package com.xiaobingkj.giteer.ui.repo.commit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter4.BaseQuickAdapter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xiaobingkj.giteer.data.model.CommitBean
import com.xiaobingkj.giteer.data.model.ReleaseBean
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.RepositoryV3Bean
import com.xiaobingkj.giteer.ui.repo.release.ReleaseAdapter
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentCommitBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class CommitFragment : BaseVmDbFragment<CommitViewModel, FragmentCommitBinding>() {
    private val adapter = CommitAdapter()
    private var page: Int = 1
    private var name = ""
    override fun layoutId(): Int = R.layout.fragment_star
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            mDatabind.refreshLayout.finishRefresh()
            mDatabind.refreshLayout.finishLoadMore()
        }
        mViewModel.commitEvent.observe(viewLifecycleOwner, Observer {
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
        val repo: RepositoryBean? = arguments?.getParcelable("repo")
        name = repo!!.full_name
        mActivity.supportActionBar?.title = "提交"
        val listView = mDatabind.listView
        listView.layoutManager = LinearLayoutManager(context)
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

        val itemClickListener = object : BaseQuickAdapter.OnItemClickListener<CommitBean> {
            override fun onClick(
                adapter: BaseQuickAdapter<CommitBean, *>,
                view: View,
                position: Int
            ) {
                val bundle = Bundle()
                bundle.putString("url", adapter.getItem(position)?.html_url)
                nav().navigate(R.id.webFragment, bundle)
            }

        }
        adapter.setOnItemClickListener(itemClickListener)
    }

    fun headerRefresh() {
        page = 1
        mViewModel.getCommits(name, page)
    }

    fun footerRefresh() {
        page = page + 1
        mViewModel.getCommits(name, page)
    }

    override fun onResume() {
        super.onResume()
        mActivity.supportActionBar?.title = "提交"
        headerRefresh()
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }
}