package com.xiaobingkj.giteer.ui.me

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.xiaobingkj.giteer.data.storage.Storage
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentMeBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment

class MeFragment : BaseVmDbFragment<MeViewModel,FragmentMeBinding>() {
    override fun layoutId(): Int = R.layout.fragment_me
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            ToastUtils.showLong(it.errorLog)
        }
        mViewModel.userEvent.observe(viewLifecycleOwner) {
            val avatar = mDatabind.avatar
            if (it.avatar_url.equals("https://gitee.com/assets/no_portrait.png")) {
                avatar.setTextAndColor(it.name.substring(0, 1), R.color.gray)
            }else{
                Glide.with(mActivity).load(it.avatar_url).into(avatar)
            }
            mDatabind.name.text = it.name
            mDatabind.desc.text = it.bio
            mDatabind.time.text = TimeUtils.date2String(TimeUtils.string2Date(it.created_at, "yyyy-MM-dd'T'HH:mm:ssXXX"), "yyyy-MM-dd HH:mm:ss") + "加入"
            mDatabind.emailAddress.text = it.email
        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        mActivity.supportActionBar?.title = "我的"
        mViewModel.getUser()
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

    override fun onResume() {
        super.onResume()
        mActivity.supportActionBar?.title = "我的"
    }

}