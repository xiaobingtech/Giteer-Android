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

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.blankj.utilcode.util.ToastUtils
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.xiaobingkj.giteer.data.GithubVersion
import com.xiaobingkj.giteer.data.model.GithubVersionBean
import com.xiaobingkj.giteer.data.model.ReleaseBean
import com.xiaobingkj.giteer.ui.login.LoginViewModel
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.ActivityMainBinding
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.network.manager.NetState
import java.io.File

class MainActivity : BaseVmDbActivity<LoginViewModel, ActivityMainBinding>() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    var exitTime = 0L
    override fun layoutId(): Int = R.layout.activity_main

    override fun createObserver() {
        mViewModel.versionEvent.observe(this){
            if (it.tag_name.toInt() > GithubVersion.latest) {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("检测到新版本")
                dialog.setMessage(it.name)
                val listener = object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val url = it.assets.first().browser_download_url // 要打开的网址
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }
                }
                dialog.setPositiveButton("去更新", listener)
                dialog.setNegativeButton("取消", null)
                dialog.show()
            }
        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        setSupportActionBar(mDatabind.toolbar)

//        mDatabind.appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
//            if (Math.abs(verticalOffset) - mDatabind.appbar.totalScrollRange == 0) {
//                supportActionBar?.setDisplayShowTitleEnabled(false)
//            }else{
//                supportActionBar?.setDisplayShowTitleEnabled(true)
//            }
//        }

        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.loginFragment, R.id.tabFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.currentDestination != null && navController.currentDestination!!.id != R.id.tabFragment && navController.currentDestination!!.id != R.id.loginFragment) {
                    //如果当前界面不是主页，那么直接调用返回即可
                    navController.navigateUp()
                } else {
                    //是主页
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        ToastUtils.showShort("再按一次退出程序")
                        exitTime = System.currentTimeMillis()
                    } else {
                        finish()
                    }
                }
            }
        })

        mViewModel.getLatestVersion()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showLoading(message: String) {

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onNetworkStateChanged(netState: NetState) {
        super.onNetworkStateChanged(netState)
        if (netState.isSuccess) {
            ToastUtils.showLong("村里终于通网了")
        }else{
            ToastUtils.showLong("汤姆断网了")
        }
    }
}