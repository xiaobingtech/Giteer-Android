package com.xiaobingkj.giteer.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.*
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import com.blankj.utilcode.util.ToastUtils
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.kingja.loadsir.core.LoadService
import com.tencent.mmkv.MMKV
import com.xiaobingkj.giteer.data.GithubVersion
import com.xiaobingkj.giteer.data.storage.Storage
import com.xiaobingkj.giteer.ext.loadServiceInit
import com.xiaobingkj.giteer.ui.login.LoginViewModel
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentWelcomeBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.nav


class WelcomeFragment : BaseVmDbFragment<LoginViewModel, FragmentWelcomeBinding>() {
    override fun layoutId(): Int = R.layout.fragment_welcome
    override fun createObserver() {
        mViewModel.tokenEvent.observe(viewLifecycleOwner) {
            Storage.token = it
            nav().navigate(R.id.tabFragment)
        }
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            ToastUtils.showLong(it.errorLog)
            nav().navigate(R.id.loginFragment)
        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        if (Storage.isLogin) {
            if (Storage.token.refresh_token != null) {
                mViewModel.refreshOauthToken(Storage.token.refresh_token)
            }
        }else{
            nav().navigate(R.id.loginFragment)
        }

        requestNotificationPermission()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("910529", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                mActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun requestNotificationPermission() {
        XXPermissions.with(this)
            // 申请单个权限
            .permission(Permission.POST_NOTIFICATIONS)
            .request(object : OnPermissionCallback {

                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        ToastUtils.showLong("获取部分权限成功，但部分权限未正常授予")
                        return
                    }
//                    ToastUtils.showLong("获取通知权限成功")
                    createNotificationChannel()
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        ToastUtils.showLong("被永久拒绝授权，请手动授予通知权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(mActivity, permissions)
                    } else {
                        ToastUtils.showLong("获取通知权限失败")
                    }
                }
            })
    }


    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

}