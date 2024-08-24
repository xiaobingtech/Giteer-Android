package com.xiaobingkj.giteer.ui.me.message

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
import com.xiaobingkj.giteer.data.model.MessageBean
import com.xiaobingkj.giteer.data.model.NotificationBean
import com.xiaobingkj.giteer.ext.loadServiceInit
import com.xiaobingkj.giteer.ext.showEmpty
import com.xiaobingkj.giteer.ext.showLoading
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentNotificationBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment

class NotificationFragment : BaseVmDbFragment<NotificationViewModel, FragmentNotificationBinding>() {
    private val adapter = NotificationAdapter()
    private var page: Int = 1
    private var currentList: MutableList<NotificationBean.ListDTO>? = null
    override fun layoutId(): Int = R.layout.fragment_message
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            mDatabind.refreshLayout.finishRefresh()
            mDatabind.refreshLayout.finishLoadMore()
        }
        mViewModel.notificationEvent.observe(viewLifecycleOwner, Observer {
            if (it.list.size == 0) {
                loadsir.showEmpty()
            }else{
                loadsir.showSuccess()
            }
            if (page == 1) {
                adapter.removeAtRange(IntRange(0, adapter.itemCount - 1))
            }
            adapter.addAll(it.list)
            adapter.notifyDataSetChanged()
            if (it.list.size < 100) {
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

        val itemClickListener = object : BaseQuickAdapter.OnItemClickListener<NotificationBean.ListDTO> {
            override fun onClick(
                adapter: BaseQuickAdapter<NotificationBean.ListDTO, *>,
                view: View,
                position: Int
            ) {

            }

        }
        adapter.setOnItemClickListener(itemClickListener)
    }

    fun headerRefresh() {
        if (adapter.itemCount == 0) {
            loadsir.showLoading()
        }
        page = 1
        mViewModel.getNotificationList(page)
    }

    fun footerRefresh() {
        page = page + 1
        mViewModel.getNotificationList(page)
    }

    override fun onResume() {
        super.onResume()
        headerRefresh()
    }

    override fun lazyLoadData() {
        headerRefresh()
    }

    override fun showLoading(message: String) {

    }
}