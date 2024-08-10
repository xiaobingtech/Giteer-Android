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

package com.xiaobingkj.giteer.data.network

import com.xiaobingkj.giteer.data.model.EventBean
import com.xiaobingkj.giteer.data.model.TokenBean
import com.xiaobingkj.giteer.data.storage.Storage
import rxhttp.RxHttp
import rxhttp.toAwait
import rxhttp.toAwaitList
import rxhttp.toObservableList
import rxhttp.wrapper.coroutines.CallAwait

val HttpRequestCoroutine: HttpRequestManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManager()
}

class HttpRequestManager {

    //https://gitee.com/oauth/token?grant_type=refresh_token&refresh_token={refresh_token}
    suspend fun postOauthToken(refresh_token: String): TokenBean {
        return RxHttp.postJson("oauth/token")
            .add("grant_type", "refresh_token")
            .add("refresh_token", refresh_token)
            .toAwait<TokenBean>()
            .await()
    }

    //https://gitee.com/api/v5/users/fandongtongxue_admin/received_events?access_token=45face680d313b0749afb5a1891c245f&limit=100
    suspend fun getReceivedEvents(prev_id: Int, limit: Int = 100): MutableList<EventBean> {
        return RxHttp.get("api/v5/users/${Storage.user.login}/received_events")
            .add("prev_id", prev_id)
            .add("limit", limit)
            .toAwaitList<EventBean>()
            .await()
    }
}