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

package io.github.rosemoe.sora.langs.monarch.registry.grammardefinition

import io.github.dingyi222666.monarch.language.Language
import io.github.dingyi222666.monarch.loader.json.loadMonarchJson
import io.github.rosemoe.sora.langs.monarch.registry.FileProviderRegistry
import io.github.rosemoe.sora.langs.monarch.registry.model.GrammarDefinition

class MonarchGrammarDefinitionReader : GrammarDefinitionReader<Language>() {

    override fun readGrammar(grammarDefinition: GrammarDefinition<Unit>, path: String): Language? =
        runCatching {
            FileProviderRegistry.resolve(path)?.use {
                it.bufferedReader().readText()
            }
        }.getOrNull()?.runCatching {
            val monarchLanguage =
                loadMonarchJson(this) ?: throw Exception("Failed to load monarch source")

            Language(
                monarchLanguage = monarchLanguage,
                languageName = grammarDefinition.name,
                languageId = grammarDefinition.scopeName ?: "source${monarchLanguage.tokenPostfix}",
                fileExtensions = emptyList(),
                embeddedLanguages = grammarDefinition.embeddedLanguages,
            )
        }?.getOrNull()


}