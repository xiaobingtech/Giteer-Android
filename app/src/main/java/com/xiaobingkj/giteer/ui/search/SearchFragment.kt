package com.xiaobingkj.giteer.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.material.tabs.TabLayoutMediator
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentSearchBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class SearchFragment : BaseVmDbFragment<BaseViewModel, FragmentSearchBinding>() {
    override fun layoutId(): Int = R.layout.fragment_search
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val adpater = SearchFragmentStateAdapter(this)
        adpater.addFragment(SearchRepoFragment())
        adpater.addFragment(SearchUserFragment())
        mDatabind.viewPager.adapter = adpater

        mDatabind.etSearch.addTextChangedListener {
            if (mDatabind.viewPager.currentItem == 0) {
                val repoFragment = adpater.createFragment(0) as SearchRepoFragment
                repoFragment.onKeyChanged(it.toString())
            }else{
                val userFragment = adpater.createFragment(1) as SearchUserFragment
                userFragment.onKeyChanged(it.toString())
            }
        }

        TabLayoutMediator(mDatabind.tabs, mDatabind.viewPager, { tab, position ->
            when (position) {
                0 -> tab.text = "仓库"
                1 -> tab.text = "用户"
                else -> {}
            }
        }).attach()
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

}