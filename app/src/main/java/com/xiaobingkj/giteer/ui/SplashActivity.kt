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

package com.xiaobingkj.giteer.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.xiaobingkj.giteer.data.storage.Storage
import com.xiaobingkj.giteer.ui.login.LoginViewModel
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.ActivitySplashBinding
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.ext.nav

class SplashActivity : BaseVmDbActivity<LoginViewModel, ActivitySplashBinding>() {
    override fun layoutId(): Int = R.layout.activity_splash

    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val navController = findNavController(R.id.nav_host_fragment)
        if (Storage.isLogin) {
            if (Storage.token.refresh_token != null) {
                mViewModel.postOauthToken(Storage.token.refresh_token)
            }
            navController.navigate(R.id.tabFragment)
        }else{
            navController.navigate(R.id.loginFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showLoading(message: String) {

    }
}