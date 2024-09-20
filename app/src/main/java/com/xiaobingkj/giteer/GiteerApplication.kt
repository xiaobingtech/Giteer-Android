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
import cn.jiguang.api.utils.JCollectionAuth
import cn.jpush.android.api.JPushInterface
import com.hjq.permissions.Permission
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
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
        MMKV.initialize(this)
        //推送
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
        // 调整点二：App用户同意了隐私政策授权，并且开发者确定要开启推送服务后调用
        JCollectionAuth.setAuth(this, true); //如初始化被拦截过，将重试初始化过程
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

    }
}