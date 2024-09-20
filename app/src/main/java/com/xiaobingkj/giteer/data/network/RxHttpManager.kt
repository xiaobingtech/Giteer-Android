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

import android.app.Application
import android.util.Log
import com.tencent.mmkv.MMKV
import com.tencent.smtt.sdk.CookieManager
import com.xiaobingkj.giteer.data.storage.Storage
import io.github.rosemoe.sora.langs.textmate.BuildConfig
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.annotation.OkClient
import java.util.concurrent.TimeUnit

class RxHttpManager(context: Application) {

    fun setup() {
        val client = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .cookieJar(object: CookieJar{
                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    val cookie = CookieManager.getInstance().getCookie("gitee.com")
                    if (cookie != null) {
                        val list = parseCookieString(cookie)
                        return list
                    }
                    return listOf()
                }

                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {

                }

            })
            .build()

        RxHttpPlugins.init(client)
            .setDebug(BuildConfig.DEBUG)
            .setOnParamAssembly { p ->
                p.add("access_token", Storage.token.access_token)
                p.addHeader("Accept", "application/json, text/plain, */*")
                p.addHeader("X-Requested-With", "XMLHttpRequest")
                p.addHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
            }
    }

    private fun parseCookieString(cookieString: String): List<Cookie> {
        if (cookieString.isEmpty()) return listOf()
        // 使用分号分割字符串，得到键值对字符串数组
        val pairs = cookieString.split(";").filter { it.isNotBlank() }

        // 将每个键值对字符串转换为Cookie对象
        val cookies = pairs.map { pair ->
            // 使用等号分割键和值
            val (name, value) = pair.trim().split("=")
            Cookie.Builder()
                .domain("gitee.com")
                .name(name)
                .value(value)
                .build()
        }

        return cookies
    }


}