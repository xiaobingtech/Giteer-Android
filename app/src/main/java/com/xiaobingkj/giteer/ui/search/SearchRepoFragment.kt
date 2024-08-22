package com.xiaobingkj.giteer.ui.search

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kingja.loadsir.core.LoadService
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xiaobingkj.giteer.ext.loadServiceInit
import com.xiaobingkj.giteer.ext.showLoading
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentSearchRepoBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment


class SearchRepoFragment : BaseVmDbFragment<SearchRepoViewModel, FragmentSearchRepoBinding>() {
    override fun layoutId(): Int = R.layout.fragment_search_repo
    private val adapter = SearchRepoAdapter()
    private var page = 1
    private var q = ""
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
    override fun lazyLoadData() {

    }

    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            mDatabind.refreshLayout.finishRefresh()
            mDatabind.refreshLayout.finishLoadMore()
        }
        mViewModel.repoEvent.observe(this, Observer {
            loadsir.showSuccess()
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

        loadsir = loadServiceInit(mDatabind.refreshLayout) {
            //点击重试时触发的操作
            headerRefresh()
        }

        // 创建DividerItemDecoration并设置为水平分割线
        val dividerItemDecoration = DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL)
        listView.addItemDecoration(dividerItemDecoration)

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
        if (adapter.itemCount == 0) {
            loadsir.showLoading()
        }
        page = 1
        mViewModel.getSearchRepositories(page, q)
    }

    fun footerRefresh() {
        page = page + 1
        mViewModel.getSearchRepositories(page, q)
    }

    override fun showLoading(message: String) {

    }

    public fun onKeyChanged(q: String) {
        this.q = q
        headerRefresh()
    }

}