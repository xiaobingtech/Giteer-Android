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
import androidx.navigation.fragment.findNavController
import com.blankj.utilcode.util.ToastUtils
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebView
import com.xiaobingkj.giteer.data.storage.Storage
import com.xiaobingkj.giteer.ui.MainActivity
import com.xiaobingkj.giteer.ui.login.LoginViewModel
import com.ycbjie.webviewlib.base.X5WebChromeClient
import com.ycbjie.webviewlib.base.X5WebViewClient
import com.ycbjie.webviewlib.client.JsX5WebViewClient
import com.ycbjie.webviewlib.inter.InterWebListener
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentWebViewBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.nav

class WebFragment: BaseVmDbFragment<LoginViewModel, FragmentWebViewBinding>() {
    override fun layoutId(): Int = R.layout.fragment_web_view
    override fun lazyLoadData() {

    }

    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            ToastUtils.showLong(it.errorLog)
        }
        mViewModel.tokenEvent.observe(viewLifecycleOwner) {
            Storage.token = it
            mViewModel.getUser()
        }
        mViewModel.userEvent.observe(viewLifecycleOwner) {
            Storage.isLogin = true
            nav().navigate(R.id.tabFragment)
        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val url = arguments?.getString("url")
        if (url?.isEmpty()!!){
            ToastUtils.showLong("URL不合法")
            return
        }
        mDatabind.progress.setColor(Color.GREEN)
        mDatabind.progress.show()
        val listener = object: InterWebListener {
            override fun hindProgressBar() {
                mDatabind.progress.hide()
            }

            override fun showErrorView(type: Int) {
                ToastUtils.showLong(type.toString())
            }

            override fun startProgress(newProgress: Int) {
                mDatabind.progress.setProgress(newProgress)
            }

            override fun showTitle(title: String?) {
                mActivity.supportActionBar?.title = title
            }

        }
        mDatabind.webView.webViewClient = object: JsX5WebViewClient(mDatabind.webView, mActivity) {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                request?.url.toString().let {
                    if (it.contains("code=")) {
                        val strings = it.split("code=")
                        val code = strings.last()
                        mViewModel.getOauthToken(code)
                        return false
                    }
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        mDatabind.webView.loadUrl(url)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun showLoading(message: String) {

    }
}