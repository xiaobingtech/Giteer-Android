package com.xiaobingkj.giteer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xiaobingkj.giteer.data.storage.Storage
import com.xiaobingkj.giteer.ui.login.LoginViewModel
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentWelcomeBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.nav


class WelcomeFragment : BaseVmDbFragment<LoginViewModel, FragmentWelcomeBinding>() {
    override fun layoutId(): Int = R.layout.fragment_welcome
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        if (Storage.isLogin) {
            if (Storage.token.refresh_token != null) {
                mViewModel.postOauthToken(Storage.token.refresh_token)
            }
            nav().navigate(R.id.tabFragment)
        }else{
            nav().navigate(R.id.loginFragment)
        }
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

}