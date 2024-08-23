package com.xiaobingkj.giteer.ui.me.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.xiaobingkj.giteer.ui.trend.TrendFragmentStateAdapter
import com.xiaobingkj.giteer.ui.trend.TrendSubFragment
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentMsgBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class MsgFragment : BaseVmDbFragment<BaseViewModel, FragmentMsgBinding>() {
    override fun layoutId(): Int = R.layout.fragment_msg
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        mActivity.supportActionBar?.title = "消息通知"
        val adpater = MsgFragmentStateAdapter(this)
        adpater.addFragment(MessageFragment())
        adpater.addFragment(NotificationFragment())
        adpater.addFragment(QuanFragment())
        mDatabind.viewPager.adapter = adpater

        TabLayoutMediator(mDatabind.tabs, mDatabind.viewPager, { tab, position ->
            when (position) {
                0 -> tab.text = "私信"
                1 -> tab.text = "通知"
                2 -> tab.text = "@我"
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
        mActivity.supportActionBar?.title = "消息通知"
    }
}