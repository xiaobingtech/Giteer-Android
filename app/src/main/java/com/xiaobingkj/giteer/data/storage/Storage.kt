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

package com.xiaobingkj.giteer.data.storage

import com.google.gson.Gson
import com.tencent.mmkv.MMKV
import com.xiaobingkj.giteer.data.model.UserBean
import me.hgj.jetpackmvvm.ext.util.toJson

class Storage {
    companion object {
        var token: String
            get() {
                return MMKV.defaultMMKV().getString("token", "").toString()
            }
            set(value) {
                MMKV.defaultMMKV().putString("token", value)
            }
        var isLogin: Boolean
            get() {
                return MMKV.defaultMMKV().getBoolean("isLogin", false)
            }
            set(value) {
                MMKV.defaultMMKV().putBoolean("isLogin", value)
            }
        var user: UserBean
            get() {
                val json = MMKV.defaultMMKV().getString("token", "")
                if (json?.isEmpty() == true) {
                    return UserBean()
                }
                val gson = Gson()
                val bean = gson.fromJson<UserBean>(json, UserBean::class.java)
                return bean
            }
            set(value) {
                MMKV.defaultMMKV().putString("user", value.toJson())
            }
    }
}