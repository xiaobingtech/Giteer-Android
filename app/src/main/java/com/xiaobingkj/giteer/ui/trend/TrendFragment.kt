package com.xiaobingkj.giteer.ui.trend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.xiaobingkj.giteer.ui.search.SearchFragmentStateAdapter
import com.xiaobingkj.giteer.ui.search.SearchRepoFragment
import com.xiaobingkj.giteer.ui.search.SearchUserFragment
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentTrendBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel


class TrendFragment : BaseVmDbFragment<BaseViewModel, FragmentTrendBinding>() {
    override fun layoutId(): Int = R.layout.fragment_trend
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val adpater = TrendFragmentStateAdapter(this)
        adpater.addFragment(TrendSubFragment("api/v3/projects/featured/"))
        adpater.addFragment(TrendSubFragment("api/v3/projects/popular/"))
        adpater.addFragment(TrendSubFragment("api/v3/projects/latest/"))
        mDatabind.viewPager.adapter = adpater

        TabLayoutMediator(mDatabind.tabs, mDatabind.viewPager, { tab, position ->
            when (position) {
                0 -> tab.text = "推荐"
                1 -> tab.text = "热门"
                2 -> tab.text = "最近更新"
                else -> {}
            }
        }).attach()
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }
}