package com.xiaobingkj.giteer.ui.trend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter4.BaseQuickAdapter
import com.kingja.loadsir.core.LoadService
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xiaobingkj.giteer.data.model.RepoTreeBean
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.RepositoryV3Bean
import com.xiaobingkj.giteer.ext.loadServiceInit
import com.xiaobingkj.giteer.ext.showLoading
import com.xiaobingkj.giteer.ui.star.StarAdapter
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentTrendSubBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class TrendSubFragment(url: String) : BaseVmDbFragment<TrendSubViewModel, FragmentTrendSubBinding>() {
    private val url = url
    private val adapter = TrendSubAdapter()
    private var page: Int = 1
    override fun layoutId(): Int = R.layout.fragment_trend_sub
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
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

        loadsir = loadServiceInit(mDatabind.refreshLayout) {
            //点击重试时触发的操作
            headerRefresh()
        }

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

        val onItemClickListener = object: BaseQuickAdapter.OnItemClickListener<RepositoryV3Bean> {
            override fun onClick(
                adapter: BaseQuickAdapter<RepositoryV3Bean, *>,
                view: View,
                position: Int
            ) {
                val bundle = Bundle()
                val repoV3 = adapter.getItem(position)
                bundle.putString("name", repoV3?.path_with_namespace)
                nav().navigate(R.id.repoFragment, bundle)
            }

        }
        adapter.setOnItemClickListener(onItemClickListener)
    }

    fun headerRefresh() {
        if (adapter.itemCount == 0) {
            loadsir.showLoading()
        }
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