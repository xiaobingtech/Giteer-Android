package com.xiaobingkj.giteer.ui.search

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentSearchUserBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment

class SearchUserFragment : BaseVmDbFragment<SearchUserViewModel, FragmentSearchUserBinding>() {
    override fun layoutId(): Int = R.layout.fragment_search_user
    private val adapter = SearchUserAdapter()
    private var page = 1
    private var q = ""
    override fun lazyLoadData() {

    }

    override fun createObserver() {
        mViewModel.userEvent.observe(this, Observer {
            if (page == 1) {
                adapter.removeAtRange(IntRange(0, adapter.itemCount - 1))
            }
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
        page = 1
        mViewModel.getSearchUser(page, q)
    }

    fun footerRefresh() {
        page = page + 1
        mViewModel.getSearchUser(page, q)
    }

    override fun showLoading(message: String) {

    }

    public fun onKeyChanged(q: String) {
        this.q = q
        headerRefresh()
    }

}