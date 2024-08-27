package com.xiaobingkj.giteer.ui.repo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentForkBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment

class ForkFragment : BaseVmDbFragment<ForkViewModel, FragmentForkBinding>() {
    override fun layoutId(): Int = R.layout.fragment_fork
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

}