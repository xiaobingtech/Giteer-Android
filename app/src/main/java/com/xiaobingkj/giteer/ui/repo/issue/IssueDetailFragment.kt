package com.xiaobingkj.giteer.ui.repo.issue

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
import com.xiaobingkj.giteer.data.model.IssueBean
import com.xiaobingkj.giteer.data.model.IssueDetailBean
import com.xiaobingkj.giteer.ext.loadServiceInit
import com.xiaobingkj.giteer.ext.showLoading
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentIssueDetailBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment

class IssueDetailFragment : BaseVmDbFragment<IssueDetailViewModel, FragmentIssueDetailBinding>() {
    private var name = ""
    private var issue = ""
    private val adapter = IssueDetailAdapter()
    private var page: Int = 1
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
    override fun layoutId(): Int = R.layout.fragment_issue_detail
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            mDatabind.refreshLayout.finishRefresh()
            mDatabind.refreshLayout.finishLoadMore()
        }
        mViewModel.issueEvent.observe(this, Observer {
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
        issue = arguments?.getString("issue")!!
        name = arguments?.getString("name")!!

        loadsir = loadServiceInit(mDatabind.refreshLayout) {
            //点击重试时触发的操作
            headerRefresh()
        }

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

        val onItemClickListener = object: BaseQuickAdapter.OnItemClickListener<IssueDetailBean> {
            override fun onClick(
                adapter: BaseQuickAdapter<IssueDetailBean, *>,
                view: View,
                position: Int
            ) {
//                val bundle = Bundle()
//                val repoV3 = adapter.getItem(position)
//                bundle.putParcelable("repoV3", repoV3)
//                nav().navigate(R.id.repoFragment, bundle)
            }

        }
        adapter.setOnItemClickListener(onItemClickListener)
        headerRefresh()
    }

    fun headerRefresh() {
        if (adapter.itemCount == 0) {
            loadsir.showLoading()
        }
        page = 1
        mViewModel.getIssueDetail(name, issue, page)
    }

    fun footerRefresh() {
        page = page + 1
        mViewModel.getIssueDetail(name, issue, page)
    }

    override fun lazyLoadData() {
        headerRefresh()
    }

    override fun showLoading(message: String) {

    }
}