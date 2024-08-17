package com.xiaobingkj.giteer.ui.repo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentVideoBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import xyz.doikki.videocontroller.StandardVideoController

class VideoFragment : BaseVmDbFragment<BaseViewModel, FragmentVideoBinding>() {
    override fun layoutId(): Int = R.layout.fragment_video
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val url = arguments?.getString("url")
        mDatabind.player.setUrl(url)
        val controller = StandardVideoController(mActivity)
        controller.addDefaultControlComponent("标题", false)
        mDatabind.player.setVideoController(controller)
        mDatabind.player.start()
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

    override fun onPause() {
        super.onPause()
        mDatabind.player.pause()
    }

    override fun onResume() {
        super.onResume()
        mDatabind.player.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
//        mDatabind.player.release()
    }

}