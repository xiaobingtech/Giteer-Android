/*******************************************************************************
 *    sora-editor - the awesome code editor for Android
 *    https://github.com/Rosemoe/sora-editor
 *    Copyright (C) 2020-2024  Rosemoe
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 *
 *     Please contact Rosemoe by email 2073412493@qq.com if you need
 *     additional information or have any questions
 ******************************************************************************/

package com.xiaobingkj.giteer.ui.repo.release

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter4.BaseQuickAdapter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xiaobingkj.giteer.data.model.ReleaseBean
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.RepositoryV3Bean
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentReleaseBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class ReleaseFragment : BaseVmDbFragment<ReleaseViewModel, FragmentReleaseBinding>() {
    private val adapter = ReleaseAdapter()
    private var page: Int = 1
    private var name = ""
    override fun layoutId(): Int = R.layout.fragment_star
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            mDatabind.refreshLayout.finishRefresh()
            mDatabind.refreshLayout.finishLoadMore()
        }
        mViewModel.releaseEvent.observe(viewLifecycleOwner, Observer {
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
        mActivity.supportActionBar?.title = "发行版"
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

        val itemClickListener = object : BaseQuickAdapter.OnItemClickListener<ReleaseBean> {
            override fun onClick(
                adapter: BaseQuickAdapter<ReleaseBean, *>,
                view: View,
                position: Int
            ) {
                val bundle = Bundle()
                bundle.putParcelable("bean", adapter.getItem(position))
                nav().navigate(R.id.releaseDetailFragment, bundle)
            }

        }
        adapter.setOnItemClickListener(itemClickListener)
    }

    fun headerRefresh() {
        page = 1
        mViewModel.getReleases(name, page)
    }

    fun footerRefresh() {
        page = page + 1
        mViewModel.getReleases(name, page)
    }

    override fun onResume() {
        super.onResume()
        mActivity.supportActionBar?.title = "发行版"
        headerRefresh()
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }
}