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

package com.xiaobingkj.giteer.ui.repo

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.xiaobingkj.giteer.data.model.BranchBean
import com.xiaobingkj.giteer.data.model.PermissionBean
import com.xiaobingkj.giteer.data.model.ReadMeBean
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.network.HttpRequestCoroutine
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.network.AppException

class RepoViewModel: BaseViewModel() {
    val errorEvent = MutableLiveData<AppException>()
    val repoEvent = MutableLiveData<RepositoryBean>()
    val readMeEvent = MutableLiveData<ReadMeBean>()
    val branchEvent = MutableLiveData<List<BranchBean>>()
    val starEvent = MutableLiveData<RepositoryBean>()

    fun getRepo(name: String) {
        requestNoCheck({
            HttpRequestCoroutine.getRepo(name)
        }, {
            repoEvent.postValue(it)
        }, {
            errorEvent.postValue(it)
        })
    }

    fun getRepoReadme(name: String, ref: String = "") {
        requestNoCheck({
            HttpRequestCoroutine.getRepoReadme(name, ref)
        }, {
            readMeEvent.postValue(it)
        }, {
            errorEvent.postValue(it)
        })
    }

    fun getBranchs(name: String, page: Int) {
        requestNoCheck({
            HttpRequestCoroutine.getRepoBranches(name, page)
        }, {
            branchEvent.postValue(it)
        }, {
            errorEvent.postValue(it)
        })
    }

    fun putStared(name: String) {
        requestNoCheck({
            HttpRequestCoroutine.putStared(name)
        }, {
            starEvent.postValue(it)
        }, {
            errorEvent.postValue(it)
        })
    }

    fun deleteStared(name: String) {
        requestNoCheck({
            HttpRequestCoroutine.deleteStared(name)
        }, {
            starEvent.postValue(it)
        }, {
            errorEvent.postValue(it)
        })
    }
    fun putWatched(name: String, type: String = "watching") {
        requestNoCheck({
            HttpRequestCoroutine.putWatched(name, type)
        }, {
            starEvent.postValue(it)
        }, {
            errorEvent.postValue(it)
        })
    }

    fun deleteWatched(name: String) {
        requestNoCheck({
            HttpRequestCoroutine.deleteWatched(name)
        }, {
            starEvent.postValue(it)
        }, {
            errorEvent.postValue(it)
        })
    }

}