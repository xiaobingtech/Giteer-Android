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
import io.github.armcha.autolink.AutoLinkTextView
import io.github.armcha.autolink.MODE_CUSTOM
import io.github.armcha.autolink.Mode
import io.github.rosemoe.sora.app.R

class EventAdapter(): BaseQuickAdapter<EventBean, QuickViewHolder>() {
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: EventBean?) {
        val avatar = holder.getView<AvatarImageView>(R.id.avatar)
        if (item?.actor?.avatar_url.equals("https://gitee.com/assets/no_portrait.png")) {
            avatar.setTextAndColor(item?.actor?.name?.substring(0, 1), R.color.gray)
        }else{
            Glide.with(holder.itemView).load(item?.actor?.avatar_url).into(avatar)
        }
        var content: String = ""
        when (item?.type) {
            "PushEvent" -> {
                if (item.payload.commits.size > 0) {
                    content = "${item.actor.name} 推送到了分支 ${item.repo.human_name} 的 ${item.payload.ref.split("/").last()} 分支 ${item.payload.commits.first().sha.substring(0, 7)} ${item.payload.commits.first().message}"
                }else{
                    content = "${item.actor.name} 推送到了分支 ${item.repo.human_name} 的 ${item.payload.ref.split("/").last()} 分支"
                }

            }
            "MemberEvent" -> {
                content = "${item.actor.name} 加入了仓库 ${item.repo.human_name}"
            }
            "CreateEvent" -> {
                content = "${item.actor.name} 创建了仓库 ${item.repo.human_name}"
            }
            "IssueCommentEvent" -> {
                content = "${item.actor.name} 发表了新的任务评论"
            }
            "StarEvent" -> {
                content = "${item.actor.name} Star了仓库 ${item.repo.human_name}"
            }
            "ForkEvent" -> {
                content = "${item.actor.name} Fork了仓库 ${item.repo.human_name}"
            }
            "FollowEvent" -> {
                content = "${item.actor.name} 关注了"
            }
            else -> {}
        }
        var custom = MODE_CUSTOM("\\s${item?.actor?.name}\\b", "\\s${item?.repo?.human_name}\\b", "\\s${item?.payload?.ref?.split("/")?.last()}\\b")
        if (!item?.payload?.commits.isNullOrEmpty()) {
            custom = MODE_CUSTOM("\\s${item?.actor?.name}\\b", "\\s${item?.repo?.human_name}\\b", "\\s${item?.payload?.ref?.split("/")?.last()}\\b", "\\s${item?.payload?.commits?.first()?.sha?.substring(0, 7)}\\b", "\\s${item?.payload?.commits?.first()?.message}\\b")
        }

        val textView = holder.getView<AutoLinkTextView>(R.id.content)
        textView.addAutoLinkMode(custom)
        textView.customModeColor = R.color.accent
        textView.onAutoLinkClick {
            Log.d("TAG", it.originalText)
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