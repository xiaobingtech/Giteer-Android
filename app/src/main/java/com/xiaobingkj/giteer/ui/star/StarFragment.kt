package com.xiaobingkj.giteer.ui.star

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentStarBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment

class StarFragment : BaseVmDbFragment<StarViewModel, FragmentStarBinding>() {
    private val adapter = StarAdapter()
    private var prev_id: Int = 0
    override fun layoutId(): Int = R.layout.fragment_star
    override fun createObserver() {
        mViewModel.repoEvent.observe(this, Observer {
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
            if (it.size < 100) {
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
        prev_id = 0
        mViewModel.getStarred(prev_id)
    }

    fun footerRefresh() {
        mViewModel.getStarred(prev_id)
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