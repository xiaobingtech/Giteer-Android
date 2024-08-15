package com.xiaobingkj.giteer.ui.repo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentRepoBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment

class RepoFragment : BaseVmDbFragment<RepoViewModel, FragmentRepoBinding>() {
    override fun layoutId(): Int = R.layout.fragment_repo
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewModel.getRepoReadme()
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

}