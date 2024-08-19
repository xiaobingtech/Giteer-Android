package com.xiaobingkj.giteer.ui.repo

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter4.BaseQuickAdapter
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.xiaobingkj.giteer.data.model.ReleaseBean
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentReleaseDetailBinding
import io.noties.markwon.Markwon
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.image.glide.GlideImagesPlugin
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.download.DownLoadManager
import me.hgj.jetpackmvvm.ext.nav
import java.io.File

class ReleaseDetailFragment : BaseVmDbFragment<BaseViewModel, FragmentReleaseDetailBinding>() {
    private val adapter = ReleaseDetailAdapter()
    override fun layoutId(): Int = R.layout.fragment_release_detail
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val bean: ReleaseBean? = arguments?.getParcelable("bean")
        mActivity.supportActionBar?.title = bean?.name

        val markwon = Markwon.builder(mActivity)
            .usePlugin(GlideImagesPlugin.create(mActivity))
            .usePlugin(TablePlugin.create(mActivity))
            .build()
        val content = bean?.body
        val node = markwon.parse(content!!)
        val markdown = markwon.render(node)
        markwon.setParsedMarkdown(mDatabind.detail, markdown)

        val listView = mDatabind.listView
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = adapter

        adapter.addAll(bean!!.assets)
        adapter.notifyDataSetChanged()

        val itemClickListener = object : BaseQuickAdapter.OnItemClickListener<ReleaseBean.AssetsDTO> {
            override fun onClick(
                adapter: BaseQuickAdapter<ReleaseBean.AssetsDTO, *>,
                view: View,
                position: Int
            ) {
                XXPermissions.with(mActivity)
                    // 申请单个权限
//                    .permission(Permission.Group.STORAGE)
                    .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                    // 设置权限请求拦截器（局部设置）
                    //.interceptor(new PermissionInterceptor())
                    // 设置不触发错误检测机制（局部设置）
                    //.unchecked()
                    .request(object : OnPermissionCallback {

                        override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                            if (!allGranted) {
                                ToastUtils.showLong("获取部分权限成功，但部分权限未正常授予")
                                return
                            }
//                            ToastUtils.showLong("获取存储权限成功")
                            donwloadItem(adapter.getItem(position))
                        }

                        override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                            if (doNotAskAgain) {
                                ToastUtils.showLong("被永久拒绝授权，请手动授予存储权限")
                                // 如果是被永久拒绝就跳转到应用权限系统设置页面
                                XXPermissions.startPermissionActivity(mActivity, permissions)
                            } else {
                                ToastUtils.showLong("获取存储权限失败")
                            }
                        }
                    })


            }

        }

        adapter.setOnItemClickListener(itemClickListener)
    }

    fun donwloadItem(item: ReleaseBean.AssetsDTO?) {
        val request = DownloadManager.Request(Uri.parse(item?.browser_download_url))
        request.setTitle(item?.name)
        request.setDescription("下载中")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, item?.name)

        val manager = appContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = manager.enqueue(request)

        val receiver = object : BroadcastReceiver() {
            @SuppressLint("Range")
            override fun onReceive(context: Context?, intent: Intent?) {
                val receiveId = intent!!.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (receiveId == downloadId) {
                    val query = DownloadManager.Query()
                    query.setFilterById(receiveId)
                    val cursor = manager.query(query)
                    if (cursor.moveToFirst()) {
                        val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
                            val path = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
                            Log.d("TAG", "下载后的文件路径:" + path)
                            if (path.contains(".apk")) {
                                val file = File(path.replace("file://", ""))
                                checkInstallPermission(file)
                            }
                        }
                    }
                }
            }

        }
        appContext.registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    fun checkInstallPermission(file: File) {
        XXPermissions.with(mActivity)
            // 申请单个权限
//                    .permission(Permission.Group.STORAGE)
            .permission(Permission.REQUEST_INSTALL_PACKAGES)
            // 设置权限请求拦截器（局部设置）
            //.interceptor(new PermissionInterceptor())
            // 设置不触发错误检测机制（局部设置）
            //.unchecked()
            .request(object : OnPermissionCallback {

                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        ToastUtils.showLong("获取部分权限成功，但部分权限未正常授予")
                        return
                    }
//                            ToastUtils.showLong("获取存储权限成功")
                    installAPK(file)
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        ToastUtils.showLong("被永久拒绝授权，请手动授予存储权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(mActivity, permissions)
                    } else {
                        ToastUtils.showLong("获取存储权限失败")
                    }
                }
            })
    }

    fun installAPK(file: File) {
        val apkUri = FileProvider.getUriForFile(appContext,"io.github.rosemoe.sora.app.fileprovider",file)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        intent.setDataAndType(apkUri,"application/vnd.android.package-archive")
        if (intent.resolveActivity(mActivity.packageManager)!=null){
            startActivity(intent)
        }else{
            ToastUtils.showLong("没有安装权限")

        }
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

}