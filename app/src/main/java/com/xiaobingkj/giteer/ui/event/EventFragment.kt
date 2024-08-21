package com.xiaobingkj.giteer.ui.event

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter4.BaseQuickAdapter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.ui.star.StarAdapter
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentEventBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.util.TAG

class EventFragment : BaseVmDbFragment<EventViewModel, FragmentEventBinding>() {
    private val adapter = EventAdapter()
    private var prev_id: Int = 0
    private var menuIndex: Int = 0
    override fun layoutId(): Int = R.layout.fragment_event
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            ToastUtils.showLong(it.errorLog)
            mDatabind.refreshLayout.finishRefresh()
            mDatabind.refreshLayout.finishLoadMore()
        }
        mViewModel.eventEvent.observe(viewLifecycleOwner, Observer {
            if (prev_id == 0) {
                adapter.removeAtRange(IntRange(0, adapter.itemCount - 1))
            }
            prev_id = it.last().id
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
        mActivity.supportActionBar?.title = "动态"

        setHasOptionsMenu(true)

        val listView = mDatabind.listView
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = adapter

        adapter.onAutoLinkClick = {
            Log.d(TAG, it.transformedText)
            if (it.transformedText.contains("/")) {
                val bundle = Bundle()
                bundle.putString("name", it.transformedText.substring(1, it.transformedText.length))
                nav().navigate(R.id.repoFragment, bundle)
            }
        }

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
        if (menuIndex == 0) {
            mViewModel.getReceiveEvents(prev_id)
        }else{
            mViewModel.getEvents(prev_id)
        }

    }

    fun footerRefresh() {
        if (menuIndex == 0) {
            mViewModel.getReceiveEvents(prev_id)
        }else{
            mViewModel.getEvents(prev_id)
        }
    }

    override fun onResume() {
        super.onResume()
        mActivity.supportActionBar?.title = "动态"
        headerRefresh()
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_event, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.receivedEvent -> {
                menuIndex = 0
                headerRefresh()
            }
            R.id.events -> {
                menuIndex = 1
                headerRefresh()
            }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

}