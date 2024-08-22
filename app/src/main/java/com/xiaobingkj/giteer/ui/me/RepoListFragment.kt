package com.xiaobingkj.giteer.ui.me

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
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.ext.loadServiceInit
import com.xiaobingkj.giteer.ext.showLoading
import com.xiaobingkj.giteer.ui.star.StarAdapter
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentRepoListBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav


class RepoListFragment : BaseVmDbFragment<RepoListViewModel, FragmentRepoListBinding>() {
    private val adapter = StarAdapter()
    private var page: Int = 1
    private var type: String = ""
    private var name: String? = ""
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
    override fun layoutId(): Int = R.layout.fragment_repo_list
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            mDatabind.refreshLayout.finishRefresh()
            mDatabind.refreshLayout.finishLoadMore()
        }
        mViewModel.repoEvent.observe(viewLifecycleOwner, Observer {
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
        mActivity.supportActionBar?.title = "所有仓库"
        type = arguments?.getString("type")!!
        name = arguments?.getString("name")
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

        val itemClickListener = object : BaseQuickAdapter.OnItemClickListener<RepositoryBean> {
            override fun onClick(
                adapter: BaseQuickAdapter<RepositoryBean, *>,
                view: View,
                position: Int
            ) {
                val bundle = Bundle()
                val repo = adapter.getItem(position)
                bundle.putString("name", repo?.full_name)
                nav().navigate(R.id.repoFragment, bundle)
            }

        }
        adapter.setOnItemClickListener(itemClickListener)
    }

    fun headerRefresh() {
        if (adapter.itemCount == 0) {
            loadsir.showLoading()
        }
        page = 1
        requestData()
    }

    fun footerRefresh() {
        page = page + 1
        requestData()
    }

    private fun requestData() {
        when (type) {
            "my" -> {mViewModel.getMyRepoList(page)}
            "user" -> {mViewModel.getUserRepoList(name!!, page)}
            else -> {}
        }
    }

    override fun onResume() {
        super.onResume()
        mActivity.supportActionBar?.title = "所有仓库"
        headerRefresh()
    }

    override fun lazyLoadData() {
        headerRefresh()
    }

    override fun showLoading(message: String) {

    }

}