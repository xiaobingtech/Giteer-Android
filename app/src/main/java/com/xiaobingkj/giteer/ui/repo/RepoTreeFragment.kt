package com.xiaobingkj.giteer.ui.repo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import cc.shinichi.library.ImagePreview
import cc.shinichi.library.bean.ImageInfo
import com.blankj.utilcode.util.EncodeUtils
import com.unnamed.b.atv.model.TreeNode
import com.unnamed.b.atv.model.TreeNode.TreeNodeClickListener
import com.unnamed.b.atv.view.AndroidTreeView
import com.xiaobingkj.giteer.data.model.RepositoryBean
import com.xiaobingkj.giteer.data.model.RepositoryV3Bean
import io.github.rosemoe.sora.app.MainFragment
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentRepoTreeBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.ext.nav

class RepoTreeFragment : BaseVmDbFragment<RepoTreeViewModel, FragmentRepoTreeBinding>() {
    override fun layoutId(): Int = R.layout.fragment_repo_tree
    private var tView: AndroidTreeView? = null
    private var fullName: String = ""
    private val path: String = ""
    private var ref: String = ""
    private var rootNode: TreeNode? = null
    private var currentNode: TreeNode? = null
    private var currentUrl: String = ""
    private var currentItem: IconTreeItemHolder.IconTreeItem? = null
    override fun createObserver() {
        mViewModel.treeEvent.observe(viewLifecycleOwner) {
            it.forEach {
                if (currentNode != null) {
                    if (it.type.equals("dir")) {
                        tView?.addNode(currentNode, TreeNode(IconTreeItemHolder.IconTreeItem(R.string.ic_folder, it.name, it.path, it.download_url, it.sha)))
                    }else{
                        tView?.addNode(currentNode, TreeNode(IconTreeItemHolder.IconTreeItem(R.string.ic_drive_file, it.name, it.path, it.download_url, it.sha)))
                    }
                    currentNode?.setExpanded(true)
                }else{
                    if (it.type.equals("dir")) {
                        tView?.addNode(rootNode, TreeNode(IconTreeItemHolder.IconTreeItem(R.string.ic_folder, it.name, it.path, it.download_url, it.sha)))
                    }else{
                        tView?.addNode(rootNode, TreeNode(IconTreeItemHolder.IconTreeItem(R.string.ic_drive_file, it.name, it.path, it.download_url, it.sha)))
                    }
                }
            }
        }
        mViewModel.permissionEvent.observe(viewLifecycleOwner) {
            val canEdit = it.permission.equals("admin") || it.permission.equals("write")
            val bundle = Bundle()
            bundle.putString("path", currentItem?.path)
            bundle.putString("branch", ref)
            bundle.putString("name", fullName)
            bundle.putString("url", currentUrl)
            bundle.putString("sha", currentItem?.sha)
            bundle.putBoolean("canEdit", canEdit)
            nav().navigate(R.id.mainFragment, bundle)
        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val repo: RepositoryBean? = arguments?.getParcelable("repo")
        fullName = repo!!.full_name
        mActivity.supportActionBar?.title = repo!!.human_name
        ref = arguments?.getString("ref")!!

        rootNode = TreeNode.root()
        currentNode = rootNode

        tView = AndroidTreeView(mActivity, rootNode)
        tView?.setDefaultAnimation(true)
        tView?.setDefaultContainerStyle(R.style.TreeNodeStyleCustom)
        tView?.setDefaultViewHolder(IconTreeItemHolder::class.java)
        val nodeClickListener = object: TreeNodeClickListener {
            override fun onClick(node: TreeNode?, value: Any?) {
                val item = value as IconTreeItemHolder.IconTreeItem
                if (item.icon.equals(R.string.ic_folder)) {
                    currentNode = node
                    requestFoldData(item.path)
                }else{
                    currentItem = item
                    var parts = item.downloadURL.split("/").toMutableList()
                    parts[parts.size - 1] = EncodeUtils.urlEncode(item.name)
                    val encodeUrl = parts.joinToString("/")
                    val pathLowercase = item.path.lowercase()
                    currentUrl = encodeUrl
                    val bundle = Bundle()
                    bundle.putString("url", encodeUrl)
                    if (pathLowercase.endsWith("jpg") || pathLowercase.endsWith("png") || pathLowercase.endsWith("webp") || pathLowercase.endsWith("gif")) {
                        val imageInfoList = ArrayList<ImageInfo>()
                        val imageInfo = ImageInfo()
                        imageInfo.thumbnailUrl = encodeUrl
                        imageInfo.originUrl = encodeUrl
                        imageInfoList.add(imageInfo)
                        ImagePreview.instance.setContext(mActivity)
                            .setIndex(0)
                            .setImageInfoList(imageInfoList)
                            .start()
                    }else if (pathLowercase.endsWith("mp4")) {
                        nav().navigate(R.id.videoFragment, bundle)
                    }else{
                        mViewModel.getPermission(repo.full_name)
                    }
                }
            }

        }
        tView?.setDefaultNodeClickListener(nodeClickListener)
        val containerView = mDatabind.container
        containerView.addView(tView?.view)

        if (savedInstanceState != null) {
            val state = savedInstanceState.getString("tState")
            if (!TextUtils.isEmpty(state)) {
                tView?.restoreState(state)
            }
        }


    }

    override fun lazyLoadData() {
        mViewModel.getRepoContents(fullName, path, ref)
    }

    override fun showLoading(message: String) {

    }

    fun requestFoldData(path: String) {
        if (currentNode?.children?.size == 0) {
            mViewModel.getRepoContents(fullName, path, ref)
        }else{
            currentNode?.isSelected = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tView?.removeNode(rootNode)
    }

}