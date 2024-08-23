/*******************************************************************************
 *    sora-editor - the awesome code editor for Android
 *    https://github.com/Rosemoe/sora-editor
 *    Copyright (C) 2020-2024  Rosemoe
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 *
 *     Please contact Rosemoe by email 2073412493@qq.com if you need
 *     additional information or have any questions
 ******************************************************************************/

package com.xiaobingkj.giteer.ui.event

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.xiaobingkj.giteer.data.model.EventBean
import com.xiaobingkj.giteer.data.model.RepositoryBean
import io.github.armcha.autolink.AutoLinkItem
import io.github.armcha.autolink.AutoLinkTextView
import io.github.armcha.autolink.MODE_CUSTOM
import io.github.armcha.autolink.Mode
import io.github.rosemoe.sora.app.R
import me.hgj.jetpackmvvm.ext.util.TAG

class EventAdapter(): BaseQuickAdapter<EventBean, QuickViewHolder>() {
    var onAutoLinkClick: ((AutoLinkItem) -> Unit)? = null

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: EventBean?) {
        val avatar = holder.getView<AvatarImageView>(R.id.avatar)
        if (item?.actor?.avatar_url.equals("https://gitee.com/assets/no_portrait.png")) {
            avatar.setTextAndColor(item?.actor?.name?.substring(0, 1), R.color.gray)
        }else{
            Glide.with(holder.itemView).load(item?.actor?.avatar_url).into(avatar)
        }
        var content: String = "${item?.actor?.name}"
        var custom: MODE_CUSTOM = MODE_CUSTOM("${item?.actor?.name}\\b")
        when (item?.type) {
            "PushEvent" -> {
                if (item.payload.commits.size > 0) {
                    content = "${item.actor.name} 推送到了分支 ${item.repo.full_name} 的 ${item.payload.ref.split("/").last()} 分支 ${item.payload.commits.first().sha.substring(0, 7)} ${item.payload.commits.first().message}"
                    custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.repo.full_name}\\b", "${item.payload.commits.first().sha.substring(0, 7)}\\b", "${item.payload.commits.first().message}\\b")
                }else{
                    content = "${item.actor.name} 推送到了分支 ${item.repo.full_name} 的 ${item.payload.ref.split("/").last()} 分支"
                    custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.repo.full_name}\\b", "${item.payload.ref.split("/").last()}\\b")
                }

            }
            "MemberEvent" -> {
                content = "${item.actor.name} 加入了仓库 ${item.repo.full_name}"
                custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.repo.full_name}\\b")
            }
            "CreateEvent" -> {
                if (item.payload.ref_type.equals("branch")) {
                    content = "${item.actor.name} 创建了仓库 ${item.repo.full_name} 的 ${item.payload.ref} 分支"
                    custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.repo.full_name}\\b", "${item.payload.ref}\\b")
                }else{
                    content = "${item.actor.name} 创建了仓库 ${item.repo.full_name}"
                    custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.repo.full_name}\\b")
                }

            }
            "IssueCommentEvent" -> {
                content = "${item.actor.name} 发表了新的任务评论"
                custom = MODE_CUSTOM("${item.actor.name}\\b")
            }
            "StarEvent" -> {
                content = "${item.actor.name} Star了仓库 ${item.repo.full_name}"
                custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.repo.full_name}\\b")
            }
            "ForkEvent" -> {
                content = "${item.actor.name} Fork了仓库 ${item.repo.full_name}"
                custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.repo.full_name}\\b")
            }
            "FollowEvent" -> {
                content = "${item.actor.name} 关注了 ${item.payload.target.name}"
                custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.payload.target.name}\\b")
            }
            "PullRequestEvent" -> {
                content = "${item.actor.name} ${item.payload.action} 了Pull Request ${item.repo.full_name} 的 ${item.payload.title} ${item.payload.head.user.name}:${item.payload.head.ref} -> ${item.payload.base.user.name}:${item.payload.base.ref}"
                custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.repo.full_name}\\b", "${item.payload.title}\\b", "${item.payload.user.name}\\b", "${item.payload.head.ref}\\b", "${item.payload.base.user.name}\\b", "${item.payload.base.ref}\\b")
            }
            "PullRequestCommentEvent" -> {
                content = "${item.actor.name} 发表了新的Pull Request评论 ${item.payload.repository.full_name} 的 ${item.payload.pull_request.title} ${item.payload.comment.body}"
                custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.payload.repository.full_name}\\b", "${item.payload.pull_request.title}\\b", "${item.payload.comment.body}\\b")
            }
            "IssueEvent" -> {
                content = "${item.actor.name} ${item.payload.action} 了 ${item.payload.title}"
                custom = MODE_CUSTOM("${item.actor.name}\\b", "${item.payload.title}\\b")
            }
            else -> {
                item?.type?.let { Log.d("EventBean", it) }
            }
        }



        val textView = holder.getView<AutoLinkTextView>(R.id.content)
        textView.addAutoLinkMode(custom)
        textView.customModeColor = R.color.accent
        textView.onAutoLinkClick {
            onAutoLinkClick?.let { it1 -> it1(it) }
        }

        holder.setText(R.id.content, content)
        holder.setText(R.id.time, TimeUtils.date2String(TimeUtils.string2Date(item?.created_at, "yyyy-MM-dd'T'HH:mm:ssXXX"), "yyyy-MM-dd HH:mm:ss"))
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_event, parent)
    }
}