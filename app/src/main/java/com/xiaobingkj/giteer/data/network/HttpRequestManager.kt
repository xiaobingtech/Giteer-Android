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

import androidx.lifecycle.MutableLiveData
import com.xiaobingkj.giteer.data.model.BranchBean
import com.xiaobingkj.giteer.data.model.CommitBean
import com.xiaobingkj.giteer.data.model.EventBean
import com.xiaobingkj.giteer.data.model.IssueBean
import com.xiaobingkj.giteer.data.model.PullRequestBean
import com.xiaobingkj.giteer.data.model.ReadMeBean
import com.xiaobingkj.giteer.data.model.ReleaseBean
import com.xiaobingkj.giteer.data.model.RepoTreeBean
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.RepositoryV3Bean
import com.xiaobingkj.giteer.data.model.TokenBean
import com.xiaobingkj.giteer.data.model.UserBean
import com.xiaobingkj.giteer.data.storage.Storage
import rxhttp.RxHttp
import rxhttp.toAwait
import rxhttp.toAwaitList

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


    suspend fun getUser(): UserBean {
        return RxHttp.get("api/v5/user")
            .toAwait<UserBean>()
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

    suspend fun getEvents(prev_id: Int, limit: Int = 100): MutableList<EventBean> {
        return RxHttp.get("api/v5/users/${Storage.user.login}/events")
            .add("prev_id", prev_id)
            .add("limit", limit)
            .toAwaitList<EventBean>()
            .await()
    }

    //https://gitee.com/api/v5/users/fandongtongxue_admin/starred?access_token=8b54574d49a35d59fcbff25c54d3e934&limit=20&sort=created&direction=desc
    suspend fun getStarred(prev_id: Int, sort: String = "created", direction: String = "desc", limit: Int = 100): MutableList<RepositoryBean> {
        return RxHttp.get("api/v5/users/${Storage.user.login}/starred")
            .add("prev_id", prev_id)
            .add("sort", sort)
            .add("direction", direction)
            .add("limit", limit)
            .toAwaitList<RepositoryBean>()
            .await()
    }
    //搜索仓库
    suspend fun getSearchRepositories(page: Int, q: String, order: String = "desc", perpage: Int = 100): MutableList<RepositoryBean> {
        return RxHttp.get("api/v5/search/repositories")
            .add("page", page)
            .add("q",q)
            .add("perpage", perpage)
            .add("order", order)
            .toAwaitList<RepositoryBean>()
            .await()
    }
    //搜索用户
    suspend fun getSearchUser(page: Int, q: String, order: String = "desc", perpage: Int = 100): MutableList<UserBean> {
        return RxHttp.get("api/v5/search/users")
            .add("page", page)
            .add("q",q)
            .add("perpage", perpage)
            .add("order", order)
            .toAwaitList<UserBean>()
            .await()
    }
    suspend fun getTrendRepositories(url: String, page: Int): MutableList<RepositoryV3Bean> {
        return RxHttp.get(url)
            .add("page", page)
            .toAwaitList<RepositoryV3Bean>()
            .await()
    }
    //
    suspend fun getRepoReadme(name: String, ref: String): ReadMeBean {
        return RxHttp.get("api/v5/repos/${name}/readme")
            .add("ref", ref)
            .toAwait<ReadMeBean>()
            .await()
    }

    //https://gitee.com/api/v5/repos/GiteerApp/GiteerAndroid/branches?access_token=f4a6b6925761ba09c734c75783bf3de7&sort=name&direction=asc&page=1
    suspend fun getRepoBranches(name: String, page: Int): MutableList<BranchBean> {
        return RxHttp.get("api/v5/repos/${name}/branches")
            .add("sort", "name")
            .add("direction", "asc")
            .add("page", page)
            .toAwaitList<BranchBean>()
            .await()
    }

    suspend fun getRepoContents(name: String, path: String = "", ref: String): MutableList<RepoTreeBean> {
        return RxHttp.get("api/v5/repos/${name}/contents/${path}")
            .add("ref", ref)
            .toAwaitList<RepoTreeBean>()
            .await()
    }

    suspend fun getRepoFile(url: String): String {
        return RxHttp.get(url)
            .toAwait<String>()
            .await()
    }
    //https://gitee.com/api/v5/repos/gfdgd-xi/waydroid-runner/releases?access_token=f6ea55ecc1106afaf9a985aea700334b&page=1&per_page=20&direction=desc
    suspend fun getReleases(name: String, page: Int, perpage: Int = 100): MutableList<ReleaseBean> {
        return RxHttp.get("api/v5/repos/${name}/releases")
            .add("page", page)
            .add("per_page", perpage)
            .add("direction", "desc")
            .toAwaitList<ReleaseBean>()
            .await()
    }
    //https://gitee.com/api/v5/repos/GiteerApp/GiteerAndroid/commits?access_token=f6ea55ecc1106afaf9a985aea700334b&page=1&per_page=20
    suspend fun getCommits(name: String, page: Int, perpage: Int = 100): MutableList<CommitBean> {
        return RxHttp.get("api/v5/repos/${name}/commits")
            .add("page", page)
            .add("per_page", perpage)
            .toAwaitList<CommitBean>()
            .await()
    }
    suspend fun getIssue(name: String, state: String, page: Int): MutableList<IssueBean> {
        return RxHttp.get("api/v5/repos/${name}/issues")
            .add("page", page)
            .add("state", state)
            .toAwaitList<IssueBean>()
            .await()
    }
    suspend fun getPulls(name: String, state: String, page: Int): MutableList<PullRequestBean> {
        return RxHttp.get("api/v5/repos/${name}/pulls")
            .add("page", page)
            .add("state", state)
            .toAwaitList<PullRequestBean>()
            .await()
    }


}
