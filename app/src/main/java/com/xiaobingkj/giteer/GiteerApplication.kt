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

package com.xiaobingkj.giteer

import android.app.Application
import android.content.Context
import android.util.Log
import com.android.tony.defenselib.DefenseCrash
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.maning.librarycrashmonitor.MCrashMonitor
import com.maning.librarycrashmonitor.listener.MCrashCallBack
import com.tencent.mmkv.MMKV
import com.xiaobingkj.giteer.data.network.RxHttpManager
import com.xiaobingkj.giteer.ui.MainActivity
import com.ycbjie.webviewlib.utils.X5WebUtils
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.EmptyCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.ErrorCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.LoadingCallback
import java.io.File


class GiteerApplication: Application() {

    private val TAG = "GiteerApplication"

    override fun onCreate() {
        super.onCreate()
        //初始化MMKV
        val rootDir = MMKV.initialize(this)
        Log.d(TAG, "MMKV rootDir:" + rootDir)
        //初始化YCWebView
        X5WebUtils.init(this)
        //初始化RxHttp
        RxHttpManager(this).setup()

        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())//加载
            .addCallback(ErrorCallback())//错误
            .addCallback(EmptyCallback())//空
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()

        initCrash()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DefenseCrash.initialize(this)
        DefenseCrash.install { thread, throwable, isSafeMode, isCrashInChoreographer ->
            Log.d("Exceptionhandler",
                "thread:${thread.name}"+
                        "exception:${throwable.message}"+
                        "isCrashInChoreographer:$isCrashInChoreographer"+
                        "isSafeMode:$isSafeMode")
        }
    }

    private fun initCrash() {
        /**
         * 初始化日志系统
         * context :    上下文
         * isDebug :    是不是Debug模式,true:崩溃后显示自定义崩溃页面 ;false:关闭应用,不跳转奔溃页面(默认)
         * CrashCallBack : 回调执行
         */
        MCrashMonitor.init(this, true, object : MCrashCallBack {
            override fun onCrash(file: File) {
                //可以在这里保存标识，下次再次进入把日志发送给服务器
                Log.d("CrashMonitor回调", file.absolutePath)
            }
        })
    }
}