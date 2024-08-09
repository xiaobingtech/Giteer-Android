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

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tencent.mmkv.MMKV
import com.xiaobingkj.giteer.data.Constants
import com.xiaobingkj.giteer.ui.TabActivity
import com.xiaobingkj.giteer.ui.webview.WebViewActivity
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.ActivityLoginBinding
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity

class LoginActivity : BaseVmDbActivity<LoginViewModel, ActivityLoginBinding>() {
    override fun layoutId(): Int = R.layout.activity_login

    override fun createObserver() {

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
            val intent = Intent(this@LoginActivity, WebViewActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }

        fun loginByPersonalToken() {
            val view = layoutInflater.inflate(R.layout.dialog_edittext, findViewById(R.id.item_cons_edit_text))
            val eT = view.findViewById<EditText>(R.id.edit_text)
            AlertDialog.Builder(this@LoginActivity)
                .setMessage("隐私声明：Giteer不会从您的Gitee账号收集任何信息，请您放心使用。")
                .setView(view)
                .setNegativeButton("取消") {_, _ ->

                }
                .setPositiveButton("确定") {_, _->
                    val token = eT.text.toString()
                    if (token.isNotEmpty()){
                        MMKV.defaultMMKV().putString("token", token)
                        startActivity(Intent(this@LoginActivity, TabActivity::class.java))
                    }else{
                        Toast.makeText(this@LoginActivity, "token不合法", Toast.LENGTH_LONG).show()
                    }
                }
                .show()
        }
    }
}