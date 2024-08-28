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

import com.xiaobingkj.giteer.data.Constants
import com.xiaobingkj.giteer.data.model.BranchBean
import com.xiaobingkj.giteer.data.model.CommitBean
import com.xiaobingkj.giteer.data.model.ContributionBean
import com.xiaobingkj.giteer.data.model.EventBean
import com.xiaobingkj.giteer.data.model.EventBean.OrgDTO
import com.xiaobingkj.giteer.data.model.GithubVersionBean
import com.xiaobingkj.giteer.data.model.IssueBean
import com.xiaobingkj.giteer.data.model.IssueDetailBean
import com.xiaobingkj.giteer.data.model.MessageBean
import com.xiaobingkj.giteer.data.model.NotificationBean
import com.xiaobingkj.giteer.data.model.PermissionBean
import com.xiaobingkj.giteer.data.model.PullRequestBean
import com.xiaobingkj.giteer.data.model.ReadMeBean
import com.xiaobingkj.giteer.data.model.ReleaseBean
import com.xiaobingkj.giteer.data.model.RepoTreeBean
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.RepositoryBean.EnterpriseDTO
import com.xiaobingkj.giteer.data.model.RepositoryV3Bean
import com.xiaobingkj.giteer.data.model.SendMessageBean
import com.xiaobingkj.giteer.data.model.TokenBean
import com.xiaobingkj.giteer.data.model.User
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
    suspend fun refreshOauthToken(refresh_token: String): TokenBean {
        return RxHttp.postJson("oauth/token")
            .add("grant_type", "refresh_token")
            .add("refresh_token", refresh_token)
            .toAwait<TokenBean>()
            .await()
    }

    suspend fun getOauthToken(code: String): TokenBean {
        return RxHttp.postJson("oauth/token")
            .add("code", code)
            .add("client_id", Constants.CLIENT_ID)
            .add("client_secret", Constants.CLIENT_SECRET)
            .add("redirect_uri", Constants.REDIRECT_URI)
            .add("grant_type", "authorization_code")
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
    suspend fun getCommits(name: String, sha: String, page: Int, perpage: Int = 100): MutableList<CommitBean> {
        return RxHttp.get("api/v5/repos/${name}/commits")
            .add("sha", sha)
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

    suspend fun getRepo(name: String): RepositoryBean {
        return RxHttp.get("api/v5/repos/${name}")
            .toAwait<RepositoryBean>()
            .await()
    }

    suspend fun getUser(name: String): UserBean {
        return RxHttp.get("api/v5/users/${name}")
            .toAwait<UserBean>()
            .await()
    }
    //https://gitee.com/api/v5/user/repos
    suspend fun getMyRepoList(page: Int, perpage: Int = 100): MutableList<RepositoryBean> {
        return RxHttp.get("api/v5/user/repos")
            .add("page", page)
            .add("per_page", perpage)
            .toAwaitList<RepositoryBean>()
            .await()
    }
    //https://gitee.com/api/v5/users/{username}/repos
    suspend fun getUserRepoList(name: String, page: Int, perpage: Int = 100): MutableList<RepositoryBean> {
        return RxHttp.get("api/v5/users/${name}/repos")
            .add("page", page)
            .add("per_page", perpage)
            .toAwaitList<RepositoryBean>()
            .await()
    }
    //https://gitee.com/api/v5/user/
    suspend fun getMyUserList(isFollower: Boolean, page: Int, perpage: Int = 100): MutableList<UserBean> {
        return RxHttp.get("api/v5/user/${if (isFollower) {"followers"} else {"following"}}")
            .add("page", page)
            .add("per_page", perpage)
            .toAwaitList<UserBean>()
            .await()
    }
    //https://gitee.com/api/v5/users/{username}/
    suspend fun getUserUserList(isFollower: Boolean, name: String, page: Int, perpage: Int = 100): MutableList<UserBean> {
        return RxHttp.get("api/v5/users/${name}/${if (isFollower) {"followers"} else {"following"}}")
            .add("page", page)
            .add("per_page", perpage)
            .toAwaitList<UserBean>()
            .await()
    }
    //https://gitee.com/api/v5/notifications/messages?access_token=8f4149f43360eb3660398bb24df3a4e9&unread=false&page=1&per_page=100
    suspend fun getMessageList(page: Int, perpage: Int = 100): MessageBean {
        return RxHttp.get("api/v5/notifications/messages")
            .add("page", page)
            .add("per_page", perpage)
            .toAwait<MessageBean>()
            .await()
    }
    //https://gitee.com/api/v5/notifications/threads?access_token=8f4149f43360eb3660398bb24df3a4e9&type=all&page=1&per_page=20
    suspend fun getNotificationList(page: Int, perpage: Int = 100): NotificationBean {
        return RxHttp.get("api/v5/notifications/threads")
            .add("page", page)
            .add("per_page", perpage)
            .toAwait<NotificationBean>()
            .await()
    }
    //https://gitee.com/api/v5/repos/GiteerApp/GiteerFeedback/issues/I4MFUV/comments?access_token=8f4149f43360eb3660398bb24df3a4e9&page=1&per_page=20&order=asc
    suspend fun getIssueDetail(name: String, issue: String, page: Int, perpage: Int = 100): MutableList<IssueDetailBean> {
        return RxHttp.get("api/v5/repos/${name}/issues/${issue}/comments")
            .add("page", page)
            .add("per_page", perpage)
            .toAwaitList<IssueDetailBean>()
            .await()
    }
    //https://api.github.com/repos/xiaobingtech/sora-editor/releases
    suspend fun getLatestVersion(): GithubVersionBean {
        return RxHttp.get("https://api.github.com/repos/xiaobingtech/sora-editor/releases/latest")
            .setAssemblyEnabled(false)
            .toAwait<GithubVersionBean>()
            .await()
    }
    //https://gitee.com/api/v5/user/starred/{owner}/{repo} put
    suspend fun putStared(name: String): RepositoryBean {
        return RxHttp.putJson("api/v5/user/starred/${name}")
            .toAwait<RepositoryBean>()
            .await()
    }
    //https://gitee.com/api/v5/user/starred/{owner}/{repo} delete
    suspend fun deleteStared(name: String): RepositoryBean {
        return RxHttp.deleteJson("api/v5/user/starred/${name}")
            .toAwait<RepositoryBean>()
            .await()
    }
    //notifications/messages
    suspend fun sendMsg(username: String, content: String): SendMessageBean {
        return RxHttp.postJson("api/v5/notifications/messages")
            .add("username", username)
            .add("content", content)
            .toAwait<SendMessageBean>()
            .await()
    }
    //https://giteer.app.xiaobingkj.com/jpush-api-php-client/examples/push_example.php
    suspend fun push(touid: String, message: String, sender: String): String {
        return RxHttp.get("https://giteer.app.xiaobingkj.com/jpush-api-php-client/examples/push_example.php")
            .setAssemblyEnabled(false)
            .add("touid", touid)
            .add("message", message)
            .add("sender", sender)
            .toAwait<String>()
            .await()
    }

    suspend fun getPermission(name: String): PermissionBean {
        return RxHttp.get("api/v5/repos/${name}/collaborators/${Storage.user.login}/permission")
            .toAwait<PermissionBean>()
            .await()
    }
    suspend fun putWatched(name: String, type: String = "watching"): RepositoryBean {
        return RxHttp.putJson("api/v5/user/subscriptions/${name}")
            .add("type", type)
            .toAwait<RepositoryBean>()
            .await()
    }
    //https://gitee.com/api/v5/user/subscriptions/{owner}/{repo} delete
    suspend fun deleteWatched(name: String): RepositoryBean {
        return RxHttp.deleteJson("api/v5/user/subscriptions/${name}")
            .toAwait<RepositoryBean>()
            .await()
    }
    //https://gitee.com/api/v5/repos/{owner}/{repo}/forks
    suspend fun forkRepo(name: String, organization: String = ""): RepositoryBean {
        return RxHttp.get("api/v5/repos/${name}/forks")
            .add("organization", "")
            .toAwait<RepositoryBean>()
            .await()
    }
    //https://gitee.com/api/v5/users/{username}/orgs
    //https://gitee.com/api/v5/user/enterprises(admin,page,perpage)
    suspend fun getOrgs(admin: Boolean = true, page: Int, perpage: Int = 100): MutableList<OrgDTO> {
        return RxHttp.get("api/v5/user/orgs")
            .add("admin", admin)
            .add("page", page)
            .add("per_page", perpage)
            .toAwaitList<OrgDTO>()
            .await()
    }

    suspend fun getEnterprises(admin: Boolean = true, page: Int, perpage: Int = 100): MutableList<EnterpriseDTO> {
        return RxHttp.get("api/v5/user/enterprises")
            .add("admin", admin)
            .add("page", page)
            .add("per_page", perpage)
            .toAwaitList<EnterpriseDTO>()
            .await()
    }
    suspend fun getUserOrgs(name: String, admin: Boolean = true, page: Int, perpage: Int = 100): MutableList<OrgDTO> {
        return RxHttp.get("api/v5/users/${name}/orgs")
            .add("admin", admin)
            .add("page", page)
            .add("per_page", perpage)
            .toAwaitList<OrgDTO>()
            .await()
    }
    //https://gitee.com/fandongtongxue_admin?browser_history=0
    suspend fun getBrowser_history(): ContributionBean {
        return RxHttp.get("${Storage.user.login}?browser_history=0")
            .toAwait<ContributionBean>()
            .await()
    }
    //https://gitee.com/fandongtongxue_admin?browser_history=0
    suspend fun getUserBrowser_history(name: String): ContributionBean {
        return RxHttp.get("${name}?browser_history=0")
            .toAwait<ContributionBean>()
            .await()
    }
}
