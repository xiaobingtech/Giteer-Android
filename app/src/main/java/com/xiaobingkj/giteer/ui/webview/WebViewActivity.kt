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

package com.xiaobingkj.giteer.ui.webview

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.ycbjie.webviewlib.base.X5WebChromeClient
import com.ycbjie.webviewlib.base.X5WebViewClient
import com.ycbjie.webviewlib.inter.InterWebListener
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.ActivityWebViewBinding
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class WebViewActivity : BaseVmDbActivity<BaseViewModel, ActivityWebViewBinding>() {
    override fun layoutId(): Int = R.layout.activity_web_view
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val url = intent.getStringExtra("url")
        if (url?.isEmpty()!!){
            Toast.makeText(this@WebViewActivity, "URL不合法", Toast.LENGTH_LONG).show()
            return
        }
        mDatabind.webView.loadUrl(url)
        mDatabind.progress.setColor(Color.GREEN)
        mDatabind.progress.show()
        val listener = object: InterWebListener {
            override fun hindProgressBar() {
                mDatabind.progress.hide()
            }

            override fun showErrorView(type: Int) {
                Toast.makeText(this@WebViewActivity, type.toString(), Toast.LENGTH_LONG).show()
            }

            override fun startProgress(newProgress: Int) {
                mDatabind.progress.setProgress(newProgress)
            }

            override fun showTitle(title: String?) {

            }

        }
        val webClient = MyX5WebViewClient(mDatabind.webView, this)
        mDatabind.webView.webViewClient = webClient
        webClient.setWebListener(listener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun showLoading(message: String) {

    }

    inner class MyX5WebViewClient(webView: WebView?, context: Context?) :
        X5WebViewClient(webView, context) {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

            return super.shouldOverrideUrlLoading(view, url)
        }
    }
}