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

package com.xiaobingkj.giteer.ui.login

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.xiaobingkj.giteer.data.Constants
import com.xiaobingkj.giteer.data.model.TokenBean
import com.xiaobingkj.giteer.data.storage.Storage
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentLoginBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class LoginFragment: BaseVmDbFragment<LoginViewModel, FragmentLoginBinding>() {
    override fun layoutId(): Int = R.layout.fragment_login
    override fun lazyLoadData() {

    }

    override fun createObserver() {
        mViewModel.userEvent.observe(this, Observer {
            Storage.isLogin = true
            nav().navigate(R.id.tabFragment)
        })
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showLoading(message: String) {

    }

    inner class ProxyClick() {
        fun login() {
            val url = "https://gitee.com/oauth/authorize?client_id=${Constants.CLIENT_ID}&redirect_uri=${Constants.REDIRECT_URI}&response_type=code"
            val bundle = Bundle()
            bundle.putString("url", url)
            nav().navigate(R.id.webFragment, bundle)
        }

        fun loginByPersonalToken() {
            val view = layoutInflater.inflate(R.layout.dialog_edittext, getView()?.findViewById(R.id.item_cons_edit_text))
            val eT = view.findViewById<EditText>(R.id.edit_text)
            AlertDialog.Builder(mActivity)
                .setMessage("隐私声明：Giteer不会从您的Gitee账号收集任何信息，请您放心使用。")
                .setView(view)
                .setNegativeButton("取消") {_, _ ->

                }
                .setPositiveButton("确定") {_, _->
                    val token = eT.text.toString()
                    if (token.isNotEmpty()){
                        val bean = TokenBean()
                        bean.access_token = token
                        Storage.token = bean
                        mViewModel.getUser()
                    }else{
                        ToastUtils.showLong("token不合法")
                    }
                }
                .show()
        }
    }
}