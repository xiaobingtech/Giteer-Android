package com.xiaobingkj.giteer.ui.repo

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.kingja.loadsir.core.LoadService
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.RepositoryV3Bean
import com.xiaobingkj.giteer.ext.loadServiceInit
import com.xiaobingkj.giteer.ext.showLoading
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentRepoBinding
import io.noties.markwon.Markwon
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.image.glide.GlideImagesPlugin
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class RepoFragment : BaseVmDbFragment<RepoViewModel, FragmentRepoBinding>() {
    private var name: String = ""
    private var repo: RepositoryBean? = null
    private var ref: String = ""
    override fun layoutId(): Int = R.layout.fragment_repo
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
    override fun createObserver() {
        mViewModel.errorEvent.observe(viewLifecycleOwner) {
            ToastUtils.showLong(it.errorLog)
        }
        mViewModel.repoEvent.observe(viewLifecycleOwner) {
            loadsir.showSuccess()
            repo = it
            ref = it.default_branch

            mActivity.supportActionBar?.title = it.human_name

            val avatar = mDatabind.avatar
            if (it.owner.avatar_url.equals("https://gitee.com/assets/no_portrait.png")) {
                avatar.setTextAndColor(it.owner.name.substring(0, 1), R.color.gray)
            }else{
                Glide.with(mActivity).load(it.owner.avatar_url).into(avatar)
            }

            mDatabind.name.text = it.human_name
            mDatabind.desc.text = it.description
            mDatabind.time.text = TimeUtils.date2String(TimeUtils.string2Date(it.updated_at, "yyyy-MM-dd'T'HH:mm:ssXXX"), "yyyy-MM-dd HH:mm:ss")
            if (it.description == null) {
                mDatabind.desc.visibility = View.GONE
            }
            mDatabind.btnStar.text = if (it.stared) {"Starred"} else {"Star"}
            mDatabind.btnWatch.text = if (it.watched) {"Watched"} else {"Watch"}
            mViewModel.getRepoReadme(name, ref)
        }
        mViewModel.readMeEvent.observe(viewLifecycleOwner) {
            val markwon = Markwon.builder(mActivity)
                .usePlugin(GlideImagesPlugin.create(mActivity))
                .usePlugin(TablePlugin.create(mActivity))
                .build()
            val content = String(EncodeUtils.base64Decode(it.content))
            var baseURL = "https://gitee.com/${name}/raw/${ref}/"
            val regex = Regex("\\]\\((?!http)(.*?)\\)")
            val replacedContent = content.replace(regex, "](<${baseURL}$1>)")
            val node = markwon.parse(replacedContent)
            val markdown = markwon.render(node)
            markwon.setParsedMarkdown(mDatabind.md, markdown)
        }
        mViewModel.branchEvent.observe(viewLifecycleOwner) {
            val dialog = AlertDialog.Builder(mActivity)
            dialog.setTitle("当前分支")
            val listener = object:DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    ref = it.get(which).name
                    mViewModel.getRepoReadme(name, ref)
                }
            }
            dialog.setSingleChoiceItems(it.map { it.name }.toTypedArray(), it.indexOfFirst { it.name == ref }, listener)
            dialog.setPositiveButton("确定", null)
            dialog.show()
        }
        mViewModel.starEvent.observe(viewLifecycleOwner) {

        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
        name = arguments?.getString("name")!!

        setHasOptionsMenu(false)

        loadsir = loadServiceInit(mDatabind.scrollView) {
            //点击重试时触发的操作
            requestData()
        }
    }

    fun requestData() {
        loadsir.showLoading()
        mViewModel.getRepo(name)
    }

    override fun lazyLoadData() {
        loadsir.showLoading()
        mViewModel.getRepo(name)
    }

    override fun showLoading(message: String) {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    inner class ProxyClick() {
        fun toCode() {
            val bundle = Bundle()
            bundle.putParcelable("repo", repo)
            bundle.putString("ref", ref)
            nav().navigate(R.id.repoTreeFragment, bundle)
        }
        fun toRelease() {
            val bundle = Bundle()
            bundle.putParcelable("repo", repo)
            nav().navigate(R.id.releaseFragment, bundle)
        }
        fun toIssue() {
            val bundle = Bundle()
            bundle.putParcelable("repo", repo)
            nav().navigate(R.id.issueFragment, bundle)
        }
        fun toPullRequest() {
            val bundle = Bundle()
            bundle.putParcelable("repo", repo)
            nav().navigate(R.id.pullRequestFragment, bundle)
        }
        fun toBranch() {
            mViewModel.getBranchs(name, 1)
        }
        fun toCommit() {
            val bundle = Bundle()
            bundle.putString("ref", ref)
            bundle.putParcelable("repo", repo)
            nav().navigate(R.id.commitFragment, bundle)
        }
        fun toUser() {
            val bundle = Bundle()
            bundle.putString("name", repo?.owner?.login)
            nav().navigate(R.id.userFragment, bundle)
        }

        fun operateStar() {
            if (repo!!.stared) {
                mViewModel.deleteStared(name)
                repo!!.stared = false
                mDatabind.btnStar.text = "Star"
            }else{
                mViewModel.putStared(name)
                repo!!.stared = true
                mDatabind.btnStar.text = "Starred"
            }
        }
        fun operateWatch() {
            if (repo!!.watched) {
                mViewModel.deleteWatched(name)
                repo!!.watched = false
                mDatabind.btnWatch.text = "Watch"
            }else{

                mViewModel.putWatched(name)
                repo!!.watched = true
                mDatabind.btnWatch.text = "Watched"
            }
        }
        fun operateFork() {

        }
    }

}