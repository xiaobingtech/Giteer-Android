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

package com.xiaobingkj.giteer.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xiaobingkj.giteer.ui.event.EventFragment
import com.xiaobingkj.giteer.ui.me.MeFragment
import com.xiaobingkj.giteer.ui.search.SearchFragment
import com.xiaobingkj.giteer.ui.star.StarFragment
import com.xiaobingkj.giteer.ui.trend.TrendFragment
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.ActivityTabBinding
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class TabActivity : BaseVmDbActivity<BaseViewModel, ActivityTabBinding>() {
    override fun layoutId(): Int = R.layout.activity_tab

    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.viewPager2.adapter = ViewPageAdapter(this)
        mDatabind.viewPager2.isUserInputEnabled = true

        TabLayoutMediator(mDatabind.tabLayout, mDatabind.viewPager2, { tab, position ->
            when (position) {
                0 -> tab.text = "动态"
                1 -> tab.text = "Star"
                2 -> tab.text = "搜索"
                3 -> tab.text = "趋势"
                4 -> tab.text = "我的"
                else -> {}
            }
        }).attach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showLoading(message: String) {

    }

    inner class ViewPageAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> EventFragment()
                1 -> StarFragment()
                2 -> SearchFragment()
                3 -> TrendFragment()
                4 -> MeFragment()
                else -> Fragment()
            }
        }
    }
}