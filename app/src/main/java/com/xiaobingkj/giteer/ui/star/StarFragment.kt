package com.xiaobingkj.giteer.ui.star

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter4.BaseQuickAdapter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xiaobingkj.giteer.data.model.RepositoryBean
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentStarBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class StarFragment : BaseVmDbFragment<StarViewModel, FragmentStarBinding>() {
    private val adapter = StarAdapter()
    private var prev_id: Int = 0
    override fun layoutId(): Int = R.layout.fragment_star
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            mDatabind.refreshLayout.finishRefresh()
            mDatabind.refreshLayout.finishLoadMore()
        }
        mViewModel.repoEvent.observe(viewLifecycleOwner, Observer {
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
        
        val itemClickListener = object : BaseQuickAdapter.OnItemClickListener<RepositoryBean> {
            override fun onClick(
                adapter: BaseQuickAdapter<RepositoryBean, *>,
                view: View,
                position: Int
            ) {
                val bundle = Bundle()
                val repo = adapter.getItem(position)
                bundle.putParcelable("repo", repo)
                nav().navigate(R.id.repoFragment, bundle)
            }

        }
        adapter.setOnItemClickListener(itemClickListener)
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