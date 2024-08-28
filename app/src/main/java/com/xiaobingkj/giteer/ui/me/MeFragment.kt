package com.xiaobingkj.giteer.ui.me

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.kingja.loadsir.core.LoadService
import com.xiaobingkj.giteer.data.model.TokenBean
import com.xiaobingkj.giteer.data.model.UserBean
import com.xiaobingkj.giteer.data.storage.Storage
import com.xiaobingkj.giteer.ext.loadServiceInit
import com.xiaobingkj.giteer.ext.showLoading
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentMeBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class MeFragment : BaseVmDbFragment<MeViewModel,FragmentMeBinding>() {
    override fun layoutId(): Int = R.layout.fragment_me
    private var user: UserBean? = null
    private var orgAdapter = OrgAdapter()
    private var enterpriseAdapter = EnterpriseAdapter()
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            ToastUtils.showLong(it.errorLog)
        }
        mViewModel.userEvent.observe(viewLifecycleOwner) {
            loadsir.showSuccess()
            user = it
            val avatar = mDatabind.avatar
            if (it.avatar_url.equals("https://gitee.com/assets/no_portrait.png")) {
                avatar.setTextAndColor(it.name.substring(0, 1), R.color.gray)
            }else{
                Glide.with(mActivity).load(it.avatar_url).into(avatar)
            }
            mDatabind.name.text = it.name
            mDatabind.desc.text = it.bio
            mDatabind.time.text = TimeUtils.date2String(TimeUtils.string2Date(it.created_at, "yyyy-MM-dd'T'HH:mm:ssXXX"), "yyyy-MM-dd HH:mm:ss") + "加入"
            if (it.email != null) {
                mDatabind.emailAddress.text = it.email
            }
            mDatabind.repoNum.text = it.public_repos.toString()
            mDatabind.followerNum.text = it.followers.toString()
            mDatabind.followNum.text = it.following.toString()
            Storage.user = it
        }
        mViewModel.historyEvent.observe(viewLifecycleOwner) {
            mDatabind.contribution.contributions = it.data.contribution_calendar.year_streak
        }
        mViewModel.orgEvent.observe(viewLifecycleOwner) {
            orgAdapter.removeAtRange(IntRange(0, orgAdapter.itemCount - 1))
            orgAdapter.addAll(it)
            orgAdapter.notifyDataSetChanged()
        }
        mViewModel.enterpriseEvent.observe(viewLifecycleOwner) {
            enterpriseAdapter.removeAtRange(IntRange(0, orgAdapter.itemCount - 1))
            enterpriseAdapter.addAll(it)
            enterpriseAdapter.notifyDataSetChanged()
        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        mActivity.supportActionBar?.title = "我的"
        mDatabind.click = ProxyClick()

        loadsir = loadServiceInit(mDatabind.layout) {
            //点击重试时触发的操作
            requestData()
        }

        val orgListView = mDatabind.org
        orgListView.layoutManager = LinearLayoutManager(context)
        orgListView.adapter = orgAdapter

        val enterpriseListView = mDatabind.enterprise
        enterpriseListView.layoutManager = LinearLayoutManager(context)
        enterpriseListView.adapter = enterpriseAdapter
    }

    override fun lazyLoadData() {
        requestData()
    }

    fun requestData() {
        if (user == null) {
            loadsir.showLoading()
        }
        mViewModel.getUser()
        mViewModel.getBrowser_history()
        mViewModel.getOrgs()
        mViewModel.getEnterprises()
    }

    override fun showLoading(message: String) {

    }

    override fun onResume() {
        super.onResume()
        mActivity.supportActionBar?.title = "我的"
        requestData()
    }

    inner class ProxyClick() {
        fun toRepoList() {
            val bundle = Bundle()
            bundle.putString("type", "my")
            nav().navigate(R.id.repoListFragment, bundle)
        }
        fun toFollower() {
            val bundle = Bundle()
            bundle.putString("type", "my")
            bundle.putString("action", "follower")
            nav().navigate(R.id.userListFragment, bundle)
        }

        fun toFollowing() {
            val bundle = Bundle()
            bundle.putString("type", "my")
            bundle.putString("action", "following")
            nav().navigate(R.id.userListFragment, bundle)
        }

        fun toMsg() {
            nav().navigate(R.id.msgFragment)
        }

        fun toSetting() {
            Storage.token = TokenBean()
            Storage.user = UserBean()
            Storage.isLogin = false
            nav().popBackStack(R.id.loginFragment, false)
        }
    }

}