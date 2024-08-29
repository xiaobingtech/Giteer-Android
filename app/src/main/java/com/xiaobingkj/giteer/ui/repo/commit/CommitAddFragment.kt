package com.xiaobingkj.giteer.ui.repo.commit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentCommitAddBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class CommitAddFragment : BaseVmDbFragment<CommitAddViewModel, FragmentCommitAddBinding>() {
    override fun layoutId(): Int = R.layout.fragment_commit_add
    override fun createObserver() {
        mViewModel.commitEvent.observe(viewLifecycleOwner) {
            nav().navigateUp()
        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        mActivity.supportActionBar?.title = "提交"
        mDatabind.click = ProxyClick()

    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

    inner class ProxyClick() {
        fun submit() {
            val branch = arguments?.getString("branch")!!
            val sha = arguments?.getString("sha")!!
            val name = arguments?.getString("name")!!
            val path = arguments?.getString("path")!!
            val content = arguments?.getString("content")!!
            mViewModel.addCommit(name, path, content, sha, mDatabind.message.text.toString(), branch)
        }
    }

}