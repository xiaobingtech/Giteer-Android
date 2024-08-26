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

package com.xiaobingkj.giteer.ui.me.message

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.xiaobingkj.giteer.data.model.MessageBean
import com.xiaobingkj.giteer.data.model.NotificationBean
import com.xiaobingkj.giteer.data.model.SendMessageBean
import com.xiaobingkj.giteer.data.network.HttpRequestCoroutine
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.network.AppException

class ChatViewModel: BaseViewModel() {
    val msgEvent = MutableLiveData<SendMessageBean>()
    val pushEvent = MutableLiveData<String>()
    val errorEvent = MutableLiveData<AppException>()

    fun sendMsg(username: String, content: String) {
        requestNoCheck({
            HttpRequestCoroutine.sendMsg(username, content)
        }, {
            msgEvent.postValue(it)
        }, {
            errorEvent.postValue(it)
        })
    }

    fun push(touid: String, message: String, sender: String) {
        requestNoCheck({
            HttpRequestCoroutine.push(touid, message, sender)
        }, {
            pushEvent.postValue(it)
        }, {
            errorEvent.postValue(it)
        })
    }
}