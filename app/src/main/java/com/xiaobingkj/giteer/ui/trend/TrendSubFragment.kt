package com.xiaobingkj.giteer.ui.trend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xiaobingkj.giteer.ui.star.StarAdapter
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentTrendSubBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment

class TrendSubFragment(url: String) : BaseVmDbFragment<TrendSubViewModel, FragmentTrendSubBinding>() {
    private val url = url
    private val adapter = TrendSubAdapter()
    private var page: Int = 1
    override fun layoutId(): Int = R.layout.fragment_trend_sub
    override fun createObserver() {
        mViewModel.repoEvent.observe(this, Observer {
            if (page == 1) {
                adapter.removeAtRange(IntRange(0, adapter.itemCount - 1))
            }
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
            if (it.size < 20) {
                mDatabind.refreshLayout.finishLoadMoreWithNoMoreData()
            }else{
                mDatabind.refreshLayout.finishLoadMore()
            }
            mDatabind.refreshLayout.finishRefresh()
        })
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val listView = mDatabind.listView
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = adapter

        val loadMoreListener = object: OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                headerRefresh()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                footerRefresh()
            }

        }
        mDatabind.refreshLayout.setOnRefreshLoadMoreListener(loadMoreListener)
    }

    fun headerRefresh() {
        page = 1
        mViewModel.getTrendRepositories(url, page)
    }

    fun footerRefresh() {
        page = page + 1
        mViewModel.getTrendRepositories(url, page)
    }

    override fun onResume() {
        super.onResume()
        headerRefresh()
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

}