package com.xiaobingkj.giteer.ui.star

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter4.BaseQuickAdapter
import com.kingja.loadsir.core.LoadService
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.ext.loadServiceInit
import com.xiaobingkj.giteer.ext.showEmpty
import com.xiaobingkj.giteer.ext.showLoading
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentStarBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class StarFragment : BaseVmDbFragment<StarViewModel, FragmentStarBinding>() {
    private val adapter = StarAdapter()
    private var prev_id: Int = 0
    override fun layoutId(): Int = R.layout.fragment_star
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            mDatabind.refreshLayout.finishRefresh()
            mDatabind.refreshLayout.finishLoadMore()
        }
        mViewModel.repoEvent.observe(viewLifecycleOwner, Observer {
            if (it.size == 0) {
                loadsir.showEmpty()
            }else{
                loadsir.showSuccess()
            }
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
        mActivity.supportActionBar?.title = "星标"
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
        prev_id = 0
        mViewModel.getStarred(prev_id)
    }

    fun footerRefresh() {
        mViewModel.getStarred(prev_id)
    }

    override fun onResume() {
        super.onResume()
        mActivity.supportActionBar?.title = "星标"
        headerRefresh()
    }

    override fun lazyLoadData() {
        headerRefresh()
    }

    override fun showLoading(message: String) {

    }

}