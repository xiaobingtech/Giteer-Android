package com.xiaobingkj.giteer.ui.repo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.TimeUtils
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.RepositoryV3Bean
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentRepoBinding
import io.noties.markwon.Markwon
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.image.glide.GlideImagesPlugin
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class RepoFragment : BaseVmDbFragment<RepoViewModel, FragmentRepoBinding>() {
    private var repo: RepositoryBean? = null
    private var repoV3: RepositoryV3Bean? = null
    private var ref: String = ""
    override fun layoutId(): Int = R.layout.fragment_repo
    override fun createObserver() {
        mViewModel.readMeEvent.observe(viewLifecycleOwner) {
            val markwon = Markwon.builder(mActivity)
                .usePlugin(GlideImagesPlugin.create(mActivity))
                .usePlugin(TablePlugin.create(mActivity))
                .build()
            val content = String(EncodeUtils.base64Decode(it.content))
            var baseURL = "https://gitee.com/${repo?.full_name}/raw/${ref}/"
            val regex = Regex("\\]\\((?!http)(.*?)\\)")
            val replacedContent = content.replace(regex, "](<${baseURL}$1>)")
            val node = markwon.parse(replacedContent)
            val markdown = markwon.render(node)
            markwon.setParsedMarkdown(mDatabind.md, markdown)
        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        repo = arguments?.getParcelable("repo")
        repoV3 = arguments?.getParcelable("repoV3")
        if (repo != null) {
            mDatabind.code.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("repo", repo)
                bundle.putString("ref", ref)
                nav().navigate(R.id.repoTreeFragment, bundle)
            }
            mDatabind.release.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("repo", repo)
                nav().navigate(R.id.releaseFragment, bundle)
            }

            ref = repo!!.default_branch

            mActivity.supportActionBar?.title = repo!!.human_name

            mDatabind.name.text = repo!!.human_name
            mDatabind.desc.text = repo!!.description
            mDatabind.time.text = TimeUtils.date2String(TimeUtils.string2Date(repo!!.updated_at, "yyyy-MM-dd'T'HH:mm:ssXXX"), "yyyy-MM-dd HH:mm:ss")

            mViewModel.getRepoReadme(repo!!.full_name)
        }else{
            mDatabind.code.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("repoV3", repoV3)
                bundle.putString("ref", ref)
                nav().navigate(R.id.repoTreeFragment, bundle)
            }
            mDatabind.release.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("repoV3", repoV3)
                nav().navigate(R.id.releaseFragment, bundle)
            }

            ref = repoV3!!.default_branch

            mActivity.supportActionBar?.title = repoV3!!.name_with_namespace
            mDatabind.name.text = repoV3!!.name_with_namespace
            mDatabind.desc.text = repoV3!!.description
            mDatabind.time.text = TimeUtils.date2String(TimeUtils.string2Date(repoV3!!.last_push_at, "yyyy-MM-dd'T'HH:mm:ssXXX"), "yyyy-MM-dd HH:mm:ss")

            mViewModel.getRepoReadme(repoV3!!.path_with_namespace)
        }
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

}