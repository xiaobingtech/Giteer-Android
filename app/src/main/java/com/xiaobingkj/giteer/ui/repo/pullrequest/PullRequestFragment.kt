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

package com.xiaobingkj.giteer.ui.repo.pullrequest

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.RepositoryV3Bean
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentTrendBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel


class PullRequestFragment : BaseVmDbFragment<BaseViewModel, FragmentTrendBinding>() {
    override fun layoutId(): Int = R.layout.fragment_pullrequest
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {

        val repo: RepositoryBean? = arguments?.getParcelable("repo")
        var name = repo!!.full_name

        mActivity.supportActionBar?.title = "PullRequest"

        val adpater = PullRequestFragmentStateAdapter(this)
        adpater.addFragment(PullRequestSubFragment(name,"open"))
        adpater.addFragment(PullRequestSubFragment(name,"merged"))
        adpater.addFragment(PullRequestSubFragment(name,"closed"))
        mDatabind.viewPager.adapter = adpater

        TabLayoutMediator(mDatabind.tabs, mDatabind.viewPager, { tab, position ->
            when (position) {
                0 -> tab.text = "打开"
                1 -> tab.text = "已合并"
                2 -> tab.text = "关闭"
                else -> {}
            }
        }).attach()
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

    override fun onResume() {
        super.onResume()
        mActivity.supportActionBar?.title = "PullRequest"
    }
}