/*******************************************************************************
 *    sora-editor - the awesome code editor for Android
 *    https://github.com/Rosemoe/sora-editor
 *    Copyright (C) 2020-2024  Rosemoe
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 *
 *     Please contact Rosemoe by email 2073412493@qq.com if you need
 *     additional information or have any questions
 ******************************************************************************/
package io.github.rosemoe.sora.app

import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.content.res.Configuration
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ContextMenu
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.ToastUtils
import io.github.rosemoe.sora.app.databinding.FragmentMainBinding
import io.github.rosemoe.sora.app.tests.TestActivity
import io.github.rosemoe.sora.event.ContentChangeEvent
import io.github.rosemoe.sora.event.EditorKeyEvent
import io.github.rosemoe.sora.event.KeyBindingEvent
import io.github.rosemoe.sora.event.PublishSearchResultEvent
import io.github.rosemoe.sora.event.SelectionChangeEvent
import io.github.rosemoe.sora.event.SideIconClickEvent
import io.github.rosemoe.sora.event.TextSizeChangeEvent
import io.github.rosemoe.sora.lang.EmptyLanguage
import io.github.rosemoe.sora.lang.JavaLanguageSpec
import io.github.rosemoe.sora.lang.TsLanguageJava
import io.github.rosemoe.sora.lang.diagnostic.DiagnosticRegion
import io.github.rosemoe.sora.lang.diagnostic.DiagnosticsContainer
import io.github.rosemoe.sora.langs.java.JavaLanguage
import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry
import io.github.rosemoe.sora.langs.textmate.registry.dsl.languages
import io.github.rosemoe.sora.langs.textmate.registry.model.DefaultGrammarDefinition
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver
import io.github.rosemoe.sora.text.ContentIO
import io.github.rosemoe.sora.text.LineSeparator
import io.github.rosemoe.sora.utils.CrashHandler
import io.github.rosemoe.sora.utils.codePointStringAt
import io.github.rosemoe.sora.utils.escapeCodePointIfNecessary
import io.github.rosemoe.sora.utils.toast
import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.widget.EditorSearcher.SearchOptions
import io.github.rosemoe.sora.widget.SelectionMovement
import io.github.rosemoe.sora.widget.component.EditorAutoCompletion
import io.github.rosemoe.sora.widget.component.Magnifier
import io.github.rosemoe.sora.widget.ext.EditorSpanInteractionHandler
import io.github.rosemoe.sora.widget.getComponent
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula
import io.github.rosemoe.sora.widget.schemes.SchemeEclipse
import io.github.rosemoe.sora.widget.schemes.SchemeGitHub
import io.github.rosemoe.sora.widget.schemes.SchemeNotepadXX
import io.github.rosemoe.sora.widget.schemes.SchemeVS2019
import io.github.rosemoe.sora.widget.style.LineInfoPanelPosition
import io.github.rosemoe.sora.widget.style.LineInfoPanelPositionMode
import io.github.rosemoe.sora.widget.subscribeAlways
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav
import org.eclipse.tm4e.core.registry.IGrammarSource
import org.eclipse.tm4e.core.registry.IThemeSource
import java.util.regex.PatternSyntaxException

/**
 * Demo and debug Activity for the code editor
 */
class MainFragment : BaseVmDbFragment<MainViewModel, FragmentMainBinding>() {

    private var canEdit = false

    override fun layoutId(): Int = R.layout.fragment_main
    override fun lazyLoadData() {

    }

    companion object {
        init {
            // Load tree-sitter librariesa
            System.loadLibrary("android-tree-sitter")
            System.loadLibrary("tree-sitter-java")
        }

        private const val TAG = "MainFragment"
        const val LOG_FILE = "crash-journal.log"

        /**
         * Symbols to be displayed in symbol input view
         */
        val SYMBOLS = arrayOf(
            "->", "{", "}", "(", ")",
            ",", ".", ";", "\"", "?",
            "+", "-", "*", "/", "<",
            ">", "[", "]", ":"
        )

        /**
         * Texts to be committed to editor for symbols above
         */
        val SYMBOL_INSERT_TEXT = arrayOf(
            "\t", "{}", "}", "(", ")",
            ",", ".", ";", "\"", "?",
            "+", "-", "*", "/", "<",
            ">", "[", "]", ":"
        )

    }

    private lateinit var searchMenu: PopupMenu
    private var searchOptions = SearchOptions(false, false)
    private var undo: MenuItem? = null
    private var redo: MenuItem? = null

    /**
     * Generate new [SearchOptions] for text searching in editor
     */
    private fun computeSearchOptions() {
        val caseInsensitive = !searchMenu.menu.findItem(R.id.search_option_match_case)!!.isChecked
        var type = SearchOptions.TYPE_NORMAL
        val regex = searchMenu.menu.findItem(R.id.search_option_regex)!!.isChecked
        if (regex) {
            type = SearchOptions.TYPE_REGULAR_EXPRESSION
        }
        val wholeWord = searchMenu.menu.findItem(R.id.search_option_whole_word)!!.isChecked
        if (wholeWord) {
            type = SearchOptions.TYPE_WHOLE_WORD
        }
        searchOptions = SearchOptions(type, caseInsensitive)
    }

    /**
     * Commit a text search to editor
     */
    private fun tryCommitSearch() {
        val query = mDatabind.searchEditor.editableText
        if (query.isNotEmpty()) {
            try {
                mDatabind.editor.searcher.search(
                    query.toString(),
                    searchOptions
                )
            } catch (e: PatternSyntaxException) {
                // Regex error
            }
        } else {
            mDatabind.editor.searcher.stopSearch()
        }
    }

    /**
     * Setup Textmate. Load our grammars and themes from assets
     */
    private fun setupTextmate() {
        // Add assets file provider so that files in assets can be loaded
        FileProviderRegistry.getInstance().addFileProvider(
            AssetsFileResolver(
                appContext.assets // use application context
            )
        )
        loadDefaultThemes()
        loadDefaultLanguages()
    }


    /**
     * Load default textmate themes
     */
    private /*suspend*/ fun loadDefaultThemes() /*= withContext(Dispatchers.IO)*/ {
        val themes = arrayOf("darcula", "abyss", "quietlight", "solarized_drak")
        val themeRegistry = ThemeRegistry.getInstance()
        themes.forEach { name ->
            val path = "textmate/$name.json"
            themeRegistry.loadTheme(
                ThemeModel(
                    IThemeSource.fromInputStream(
                        FileProviderRegistry.getInstance().tryGetInputStream(path), path, null
                    ), name
                ).apply {
                    if (name != "quietlight") {
                        isDark = true
                    }
                }
            )
        }

        themeRegistry.setTheme("quietlight")
    }

    /**
     * Load default languages from JSON configuration
     *
     * @see loadDefaultLanguagesWithDSL Load by Kotlin DSL
     */
    private /*suspend*/ fun loadDefaultLanguages() /*= withContext(Dispatchers.Main)*/ {
        GrammarRegistry.getInstance().loadGrammars("textmate/languages.json")
    }

    private fun loadDefaultLanguagesWithDSL() {
        GrammarRegistry.getInstance().loadGrammars(
            languages {
                language("java") {
                    grammar = "textmate/java/syntaxes/java.tmLanguage.json"
                    defaultScopeName()
                    languageConfiguration = "textmate/java/language-configuration.json"
                }
                language("kotlin") {
                    grammar = "textmate/kotlin/syntaxes/Kotlin.tmLanguage"
                    defaultScopeName()
                    languageConfiguration = "textmate/kotlin/language-configuration.json"
                }
                language("python") {
                    grammar = "textmate/python/syntaxes/python.tmLanguage.json"
                    defaultScopeName()
                    languageConfiguration = "textmate/python/language-configuration.json"
                }
            }
        )
    }

    /**
     * Re-apply color scheme
     */
    private fun resetColorScheme() {
        mDatabind.editor.apply {
            val colorScheme = this.colorScheme
            // reset
            this.colorScheme = colorScheme
        }
    }

    /**
     * Add diagnostic items to editor. For debug only.
     */
    private fun setupDiagnostics() {
        val editor = mDatabind.editor
        val container = DiagnosticsContainer()
        for (i in 0 until editor.text.lineCount) {
            val index = editor.text.getCharIndex(i, 0)
            container.addDiagnostic(
                DiagnosticRegion(
                    index,
                    index + editor.text.getColumnCount(i),
                    DiagnosticRegion.SEVERITY_ERROR
                )
            )
        }
        editor.diagnostics = container
    }

    /**
     * Ensure the editor uses a [TextMateColorScheme]
     */
    private fun ensureTextmateTheme() {
        val editor = mDatabind.editor
        var editorColorScheme = editor.colorScheme
        if (editorColorScheme !is TextMateColorScheme) {
            editorColorScheme = TextMateColorScheme.create(ThemeRegistry.getInstance())
            editor.colorScheme = editorColorScheme
        }
    }

    private fun generateKeybindingString(event: KeyBindingEvent): String {
        val sb = StringBuilder()
        if (event.isCtrlPressed) {
            sb.append("Ctrl + ")
        }

        if (event.isAltPressed) {
            sb.append("Alt + ")
        }

        if (event.isShiftPressed) {
            sb.append("Shift + ")
        }

        sb.append(KeyEvent.keyCodeToString(event.keyCode))
        return sb.toString()
    }

    /**
     * Open file from assets, and set editor text
     */
    private fun openAssetsFile(name: String) {
        /*
        lifecycleScope.launch(Dispatchers.IO) {
            val text = ContentIO.createFrom(assets.open(name))
            withContext(Dispatchers.Main) {
                mDatabind.editor.setText(text, null)

                updatePositionText()
                updateBtnState()
            }
        }
         */
    }

    /**
     * Update buttons state for undo/redo
     */
    private fun updateBtnState() {
        undo?.isEnabled = mDatabind.editor.canUndo()
        redo?.isEnabled = mDatabind.editor.canRedo()
    }

    /**
     * Update editor position tracker text
     */
    private fun updatePositionText() {
        val cursor = mDatabind.editor.cursor
        var text =
            (1 + cursor.leftLine).toString() + ":" + cursor.leftColumn + ";" + cursor.left + " "

        text += if (cursor.isSelected) {
            "(" + (cursor.right - cursor.left) + " chars)"
        } else {
            val content = mDatabind.editor.text
            if (content.getColumnCount(cursor.leftLine) == cursor.leftColumn) {
                "(<" + content.getLine(cursor.leftLine).lineSeparator.let {
                    if (it == LineSeparator.NONE) {
                        "EOF"
                    } else {
                        it.name
                    }
                } + ">)"
            } else {
                "(" + content.getLine(cursor.leftLine)
                    .codePointStringAt(cursor.leftColumn)
                    .escapeCodePointIfNecessary() + ")"
            }
        }

        // Indicator for text matching
        val searcher = mDatabind.editor.searcher
        if (searcher.hasQuery()) {
            val idx = searcher.currentMatchedPositionIndex
            val count = searcher.matchedPositionCount
            val matchText = if (count == 0) {
                "no match"
            } else if (count == 1) {
                "1 match"
            } else {
                "$count matches"
            }
            text += if (idx == -1) {
                "($matchText)"
            } else {
                "(${idx + 1} of $matchText)"
            }
        }

        mDatabind.positionDisplay.text = text
    }

    override fun createObserver() {
        mViewModel.fileEvent.observe(viewLifecycleOwner) {
            mDatabind.editor.setText(it, null)

            updatePositionText()
            updateBtnState()
        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
//        CrashHandler.INSTANCE.init(mActivity)

        // 确保显示选项菜单
        setHasOptionsMenu(true)

        val typeface = Typeface.createFromAsset(mActivity.assets, "JetBrainsMono-Regular.ttf")

        // Setup Listeners
        mDatabind.apply {
            btnGotoPrev.setOnClickListener(::gotoPrev)
            btnGotoNext.setOnClickListener(::gotoNext)
            btnReplace.setOnClickListener(::replace)
            btnReplaceAll.setOnClickListener(::replaceAll)
            searchOptions.setOnClickListener(::showSearchOptions)
        }

        canEdit = arguments?.getBoolean("canEdit") == true
        mDatabind.editor.editable = canEdit == true

        // Configure symbol input view
        val inputView = mDatabind.symbolInput
        if (canEdit == true) {
            inputView.visibility = View.VISIBLE
        }else{
            inputView.visibility = View.GONE
        }

        inputView.bindEditor(mDatabind.editor)
        inputView.addSymbols(SYMBOLS, SYMBOL_INSERT_TEXT)
        inputView.forEachButton { it.typeface = typeface }

        // Commit search when text changed
        mDatabind.searchEditor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                tryCommitSearch()
            }
        })

        // Search options
        searchMenu = PopupMenu(mActivity, mDatabind.searchOptions)
        searchMenu.inflate(R.menu.menu_search_options)
        searchMenu.setOnMenuItemClickListener {
            // Update option states
            it.isChecked = !it.isChecked
            if (it.isChecked) {
                // Regex and whole word mode can not be both chose
                when (it.itemId) {
                    R.id.search_option_regex -> {
                        searchMenu.menu.findItem(R.id.search_option_whole_word)!!.isChecked = false
                    }

                    R.id.search_option_whole_word -> {
                        searchMenu.menu.findItem(R.id.search_option_regex)!!.isChecked = false
                    }
                }
            }
            // Update search options and commit search with the new options
            computeSearchOptions()
            tryCommitSearch()
            true
        }

        // Configure editor
        mDatabind.editor.apply {
            typefaceText = typeface
            props.stickyScroll = true
            setLineSpacing(2f, 1.1f)
            nonPrintablePaintingFlags =
                CodeEditor.FLAG_DRAW_WHITESPACE_LEADING or CodeEditor.FLAG_DRAW_LINE_SEPARATOR or CodeEditor.FLAG_DRAW_WHITESPACE_IN_SELECTION
            // Update display dynamically
            // Use CodeEditor#subscribeEvent to add listeners of different events to editor
            subscribeAlways<SelectionChangeEvent> { updatePositionText() }
            subscribeAlways<PublishSearchResultEvent> { updatePositionText() }
            subscribeAlways<ContentChangeEvent> {
                postDelayedInLifecycle(
                    ::updateBtnState,
                    50
                )
            }
            subscribeAlways<SideIconClickEvent> {
                ToastUtils.showLong(R.string.tip_side_icon)
            }
            subscribeAlways<TextSizeChangeEvent> { event ->
                Log.d(
                    TAG,
                    "TextSizeChangeEvent onReceive() called with: oldTextSize = [${event.oldTextSize}], newTextSize = [${event.newTextSize}]"
                )
            }

            subscribeAlways<KeyBindingEvent> { event ->
                if (event.eventType == EditorKeyEvent.Type.DOWN) {
                    ToastUtils.showLong("Keybinding event: " + generateKeybindingString(event))
                }
            }

            // Handle span interactions
            EditorSpanInteractionHandler(this)
            getComponent<EditorAutoCompletion>()
                .setEnabledAnimation(true)
        }

        // Load textmate themes and grammars
        setupTextmate()
        // Before using Textmate Language, TextmateColorScheme should be applied
        ensureTextmateTheme()

        // Set editor language to textmate Java
        val editor = mDatabind.editor
        val language = TextMateLanguage.create(
            "source.java", true
        )
        editor.setEditorLanguage(language)

        // Open assets file
//        openAssetsFile("samples/sample.txt")

        val url = arguments?.getString("url")!!
        val name = url.split("/").last()
        mActivity.supportActionBar?.title = name
        mViewModel.getRepoFile(url)

        updatePositionText()
        updateBtnState()

        switchThemeIfRequired(mActivity, mDatabind.editor)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        switchThemeIfRequired(mActivity, mDatabind.editor)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        if (canEdit) {
            menu.add(Menu.NONE, R.id.publish, Menu.NONE, "发布更新")
        }

        undo = menu.findItem(R.id.text_undo)
        redo = menu.findItem(R.id.text_redo)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            // 在 Fragment 销毁时安全地释放编辑器资源
            mDatabind.editor?.release()
        } catch (e: Exception) {
            Log.e(TAG, "Error releasing editor", e)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val editor = mDatabind.editor
        when (id) {
            R.id.open_test_activity -> mActivity.startActivity<TestActivity>()
            R.id.open_lsp_activity -> {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    AlertDialog.Builder(mActivity)
                        .setTitle(getString(R.string.not_supported))
                        .setMessage(getString(R.string.dialog_api_warning_msg))
                        .setPositiveButton(android.R.string.ok, null)
                        .show()
                } else {
                    AlertDialog.Builder(mActivity)
                        .setTitle(R.string.dialog_lsp_entry_title)
                        .setMessage(R.string.dialog_lsp_entry_msg)
                        .setPositiveButton(R.string.choice_yes) { _, _ ->
                            mActivity.startActivity<LspTestActivity>()
                        }
                        .setNegativeButton(R.string.choice_no) { _, _ ->
                            mActivity.startActivity<LspTestJavaActivity>()
                        }
                        .setNeutralButton(android.R.string.cancel, null)
                        .show()
                }
            }

            R.id.text_undo -> editor.undo()
            R.id.text_redo -> editor.redo()
            R.id.goto_end -> editor.setSelection(
                editor.text.lineCount - 1,
                editor.text.getColumnCount(editor.text.lineCount - 1)
            )

            R.id.move_up -> editor.moveSelection(SelectionMovement.UP)
            R.id.move_down -> editor.moveSelection(SelectionMovement.DOWN)
            R.id.home -> editor.moveSelection(SelectionMovement.LINE_START)
            R.id.end -> editor.moveSelection(SelectionMovement.LINE_END)
            R.id.move_left -> editor.moveSelection(SelectionMovement.LEFT)
            R.id.move_right -> editor.moveSelection(SelectionMovement.RIGHT)
            R.id.magnifier -> {
                item.isChecked = !item.isChecked
                editor.getComponent(Magnifier::class.java).isEnabled = item.isChecked
            }

            R.id.useIcu -> {
                item.isChecked = !item.isChecked
                editor.props.useICULibToSelectWords = item.isChecked
            }

            R.id.ln_panel_fixed -> chooseLineNumberPanelPosition()

            R.id.ln_panel_follow -> {
                val themes = arrayOf(
                    getString(R.string.top),
                    getString(R.string.center),
                    getString(R.string.bottom)
                )
                AlertDialog.Builder(mActivity)
                    .setTitle(R.string.fixed)
                    .setSingleChoiceItems(themes, -1) { dialog: DialogInterface, which: Int ->
                        editor.lnPanelPositionMode = LineInfoPanelPositionMode.FOLLOW
                        when (which) {
                            0 -> editor.lnPanelPosition = LineInfoPanelPosition.TOP
                            1 -> editor.lnPanelPosition = LineInfoPanelPosition.CENTER
                            2 -> editor.lnPanelPosition = LineInfoPanelPosition.BOTTOM
                        }
                        dialog.dismiss()
                    }
                    .setNegativeButton(android.R.string.cancel, null)
                    .show()
            }

            R.id.code_format -> editor.formatCodeAsync()
            R.id.switch_language -> chooseLanguage()
            R.id.search_panel_st -> toggleSearchPanel(item)

            R.id.search_am -> {
                mDatabind.replaceEditor.setText("")
                mDatabind.searchEditor.setText("")
                editor.searcher.stopSearch()
                editor.beginSearchMode()
            }

            R.id.switch_colors -> chooseTheme()

            R.id.text_wordwrap -> {
                item.isChecked = !item.isChecked
                editor.isWordwrap = item.isChecked
            }

            R.id.completionAnim -> {
                item.isChecked = !item.isChecked
                editor.getComponent<EditorAutoCompletion>()
                    .setEnabledAnimation(item.isChecked)
            }

            R.id.open_logs -> openLogs()
            R.id.clear_logs -> clearLogs()

            R.id.editor_line_number -> {
                editor.isLineNumberEnabled = !editor.isLineNumberEnabled
                item.isChecked = editor.isLineNumberEnabled
            }

            R.id.pin_line_number -> {
                editor.setPinLineNumber(!editor.isLineNumberPinned)
                item.isChecked = editor.isLineNumberPinned
            }

            R.id.load_test_file -> {
                openAssetsFile("samples/big_sample.txt")
            }

            R.id.softKbdEnabled -> {
                editor.isSoftKeyboardEnabled = !editor.isSoftKeyboardEnabled
                item.isChecked = editor.isSoftKeyboardEnabled
            }

            R.id.disableSoftKbdOnHardKbd -> {
                editor.isDisableSoftKbdIfHardKbdAvailable =
                    !editor.isDisableSoftKbdIfHardKbdAvailable
                item.isChecked = editor.isDisableSoftKbdIfHardKbdAvailable
            }
            R.id.publish -> {
                val bundle = Bundle()
                val content = EncodeUtils.base64Encode(mDatabind.editor.text.toString())
                bundle.putString("content", String(content))
                bundle.putString("path", arguments?.getString("path"))
                bundle.putString("name", arguments?.getString("name"))
                bundle.putString("sha", arguments?.getString("sha"))
                nav().navigate(R.id.commitAddFragment, bundle)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading(message: String) {

    }

    private fun chooseLineNumberPanelPosition() {
        val editor = mDatabind.editor
        val themes = arrayOf(
            getString(R.string.top),
            getString(R.string.bottom),
            getString(R.string.left),
            getString(R.string.right),
            getString(R.string.center),
            getString(R.string.top_left),
            getString(R.string.top_right),
            getString(R.string.bottom_left),
            getString(R.string.bottom_right)
        )
        AlertDialog.Builder(mActivity)
            .setTitle(R.string.fixed)
            .setSingleChoiceItems(themes, -1) { dialog: DialogInterface, which: Int ->
                editor.lnPanelPositionMode = LineInfoPanelPositionMode.FIXED
                when (which) {
                    0 -> editor.lnPanelPosition = LineInfoPanelPosition.TOP
                    1 -> editor.lnPanelPosition = LineInfoPanelPosition.BOTTOM
                    2 -> editor.lnPanelPosition = LineInfoPanelPosition.LEFT
                    3 -> editor.lnPanelPosition = LineInfoPanelPosition.RIGHT
                    4 -> editor.lnPanelPosition = LineInfoPanelPosition.CENTER
                    5 -> editor.lnPanelPosition =
                        LineInfoPanelPosition.TOP or LineInfoPanelPosition.LEFT

                    6 -> editor.lnPanelPosition =
                        LineInfoPanelPosition.TOP or LineInfoPanelPosition.RIGHT

                    7 -> editor.lnPanelPosition =
                        LineInfoPanelPosition.BOTTOM or LineInfoPanelPosition.LEFT

                    8 -> editor.lnPanelPosition =
                        LineInfoPanelPosition.BOTTOM or LineInfoPanelPosition.RIGHT
                }
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun toggleSearchPanel(item: MenuItem) {
        if (mDatabind.searchPanel.visibility == View.GONE) {
            mDatabind.apply {
                replaceEditor.setText("")
                searchEditor.setText("")
                editor.searcher.stopSearch()
                searchPanel.visibility = View.VISIBLE
                item.isChecked = true
            }
        } else {
            mDatabind.searchPanel.visibility = View.GONE
            mDatabind.editor.searcher.stopSearch()
            item.isChecked = false
        }
    }

    private fun openLogs() {
        runCatching {
            mActivity.openFileInput(LOG_FILE).reader().readText()
        }.onSuccess {
            mDatabind.editor.setText(it)
        }.onFailure {
            ToastUtils.showLong(it.toString())
        }
    }

    private fun clearLogs() {
        runCatching {
            mActivity.openFileOutput(LOG_FILE, MODE_PRIVATE)?.use {}
        }.onFailure {
            ToastUtils.showLong(it.toString())
        }.onSuccess {
            ToastUtils.showLong(R.string.deleting_log_success)
        }
    }

    private fun chooseLanguage() {
        val editor = mDatabind.editor
        val languageOptions = arrayOf(
            "Java",
            "TextMate Java",
            "TextMate Kotlin",
            "TextMate Python",
            "TextMate Html",
            "TextMate JavaScript",
            "TextMate MarkDown",
            "TM Language from file",
            "Tree-sitter Java",
            "Text"
        )
        val tmLanguages = mapOf(
            "TextMate Java" to Pair("source.java", "source.java"),
            "TextMate Kotlin" to Pair("source.kotlin", "source.kotlin"),
            "TextMate Python" to Pair("source.java", "source.java"),
            "TextMate Html" to Pair("text.html.basic", "text.html.basic"),
            "TextMate JavaScript" to Pair("source.js", "source.js"),
            "TextMate MarkDown" to Pair("text.html.markdown", "text.html.markdown")
        )
        AlertDialog.Builder(mActivity)
            .setTitle(R.string.switch_language)
            .setSingleChoiceItems(languageOptions, -1) { dialog: DialogInterface, which: Int ->
                val selected = languageOptions[which]
                if (selected in tmLanguages) {
                    val info = tmLanguages[selected]!!
                    try {
                        ensureTextmateTheme()
                        val editorLanguage = editor.editorLanguage
                        val language = if (editorLanguage is TextMateLanguage) {
                            editorLanguage.updateLanguage(info.first)
                            editorLanguage
                        } else {
                            TextMateLanguage.create(info.second, true)
                        }
                        editor.setEditorLanguage(language)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    when (selected) {
                        "Java" -> editor.setEditorLanguage(JavaLanguage())
                        "Text" -> editor.setEditorLanguage(EmptyLanguage())
                        "TM Language from file" -> loadTMLLauncher.launch("*/*")
                        "Tree-sitter Java" -> {
                            editor.setEditorLanguage(
                                TsLanguageJava(
                                    JavaLanguageSpec(
                                        highlightScmSource = mActivity.assets.open("tree-sitter-queries/java/highlights.scm")
                                            .reader().readText(),
                                        codeBlocksScmSource = mActivity.assets.open("tree-sitter-queries/java/blocks.scm")
                                            .reader().readText(),
                                        bracketsScmSource = mActivity.assets.open("tree-sitter-queries/java/brackets.scm")
                                            .reader().readText(),
                                        localsScmSource = mActivity.assets.open("tree-sitter-queries/java/locals.scm")
                                            .reader().readText()
                                    )
                                )
                            )
                        }
                    }
                }
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun chooseTheme() {
        val editor = mDatabind.editor
        val themes = arrayOf(
            "Default",
            "GitHub",
            "Eclipse",
            "Darcula",
            "VS2019",
            "NotepadXX",
            "QuietLight for TM(VSCode)",
            "Darcula for TM",
            "Abyss for TM",
            "Solarized(Dark) for TM(VSCode)",
            "TM theme from file"
        )
        AlertDialog.Builder(mActivity)
            .setTitle(R.string.color_scheme)
            .setSingleChoiceItems(themes, -1) { dialog: DialogInterface, which: Int ->
                when (which) {
                    0 -> editor.colorScheme = EditorColorScheme()
                    1 -> editor.colorScheme = SchemeGitHub()
                    2 -> editor.colorScheme = SchemeEclipse()
                    3 -> editor.colorScheme = SchemeDarcula()
                    4 -> editor.colorScheme = SchemeVS2019()
                    5 -> editor.colorScheme = SchemeNotepadXX()
                    6 -> try {
                        ensureTextmateTheme()
                        ThemeRegistry.getInstance().setTheme("quietlight")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    7 -> try {
                        ensureTextmateTheme()
                        ThemeRegistry.getInstance().setTheme("darcula")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    8 -> try {
                        ensureTextmateTheme()
                        ThemeRegistry.getInstance().setTheme("abyss")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    9 -> try {
                        ensureTextmateTheme()
                        ThemeRegistry.getInstance().setTheme("solarized_drak")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    10 -> loadTMTLauncher.launch("*/*")
                }
                resetColorScheme()
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    fun gotoNext(view: View) {
        try {
            mDatabind.editor.searcher.gotoNext()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    fun gotoPrev(view: View) {
        try {
            mDatabind.editor.searcher.gotoPrevious()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    fun replace(view: View) {
        try {
            mDatabind.editor.searcher.replaceCurrentMatch(mDatabind.replaceEditor.text.toString())
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    fun replaceAll(view: View) {
        try {
            mDatabind.editor.searcher.replaceAll(mDatabind.replaceEditor.text.toString())
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    fun showSearchOptions(view: View) {
        searchMenu.show()
    }

    private val loadTMLLauncher = registerForActivityResult(GetContent()) { result: Uri? ->
        try {
            if (result == null) return@registerForActivityResult


            val editorLanguage = mDatabind.editor.editorLanguage

            val language = if (editorLanguage is TextMateLanguage) {
                editorLanguage.updateLanguage(
                    DefaultGrammarDefinition.withGrammarSource(
                        IGrammarSource.fromInputStream(
                            mActivity.contentResolver.openInputStream(result),
                            result.path, null
                        ),
                    )
                )
                editorLanguage
            } else {
                TextMateLanguage.create(
                    DefaultGrammarDefinition.withGrammarSource(
                        IGrammarSource.fromInputStream(
                            mActivity.contentResolver.openInputStream(result),
                            result.path, null
                        ),
                    ), true
                )
            }
            mDatabind.editor.setEditorLanguage(language)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val loadTMTLauncher = registerForActivityResult(GetContent()) { result: Uri? ->
        try {
            if (result == null) return@registerForActivityResult

            ensureTextmateTheme()

            ThemeRegistry.getInstance().loadTheme(
                IThemeSource.fromInputStream(
                    mActivity.contentResolver.openInputStream(result), result.path,
                    null
                )
            )

            resetColorScheme()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}