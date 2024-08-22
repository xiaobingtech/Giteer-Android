package com.xiaobingkj.giteer.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter4.BaseQuickAdapter
import com.kingja.loadsir.core.LoadService
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.UserBean
import com.xiaobingkj.giteer.ext.loadServiceInit
import com.xiaobingkj.giteer.ext.showLoading
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentSearchUserBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class SearchUserFragment : BaseVmDbFragment<SearchUserViewModel, FragmentSearchUserBinding>() {
    override fun layoutId(): Int = R.layout.fragment_search_user
    private val adapter = SearchUserAdapter()
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
        mViewModel.userEvent.observe(viewLifecycleOwner, Observer {
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
        val itemClickListener = object : BaseQuickAdapter.OnItemClickListener<UserBean> {
            override fun onClick(
                adapter: BaseQuickAdapter<UserBean, *>,
                view: View,
                position: Int
            ) {
                val bundle = Bundle()
                val user = adapter.getItem(position)
                bundle.putString("name", user?.login)
                nav().navigate(R.id.userFragment, bundle)
            }

        }
        adapter.setOnItemClickListener(itemClickListener)
    }

    fun headerRefresh() {
        if (adapter.itemCount == 0) {
            loadsir.showLoading()
        }
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