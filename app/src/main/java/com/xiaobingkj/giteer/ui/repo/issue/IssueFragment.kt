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

package com.xiaobingkj.giteer.ui.repo.issue

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.RepositoryV3Bean
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentTrendBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel


class IssueFragment : BaseVmDbFragment<BaseViewModel, FragmentTrendBinding>() {
    override fun layoutId(): Int = R.layout.fragment_issue
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {

        val repo: RepositoryBean? = arguments?.getParcelable("repo")
        val repoV3: RepositoryV3Bean? = arguments?.getParcelable("repoV3")
        var name = repo!!.full_name

        mActivity.supportActionBar?.title = "Issue"

        val adpater = IssueFragmentStateAdapter(this)
        adpater.addFragment(IssueSubFragment(name,"open"))
        adpater.addFragment(IssueSubFragment(name,"progressing"))
        adpater.addFragment(IssueSubFragment(name,"rejected"))
        adpater.addFragment(IssueSubFragment(name,"closed"))
        mDatabind.viewPager.adapter = adpater

        TabLayoutMediator(mDatabind.tabs, mDatabind.viewPager, { tab, position ->
            when (position) {
                0 -> tab.text = "打开"
                1 -> tab.text = "进行中"
                2 -> tab.text = "被拒绝"
                3 -> tab.text = "关闭"
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
        mActivity.supportActionBar?.title = "Issue"
    }
}