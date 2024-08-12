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

package com.xiaobingkj.giteer.ui.star

import android.content.Context
import android.view.ViewGroup
import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.xiaobingkj.giteer.data.model.RepositoryBean
import io.github.rosemoe.sora.app.R

class StarAdapter(): BaseQuickAdapter<RepositoryBean, QuickViewHolder>() {
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: RepositoryBean?) {
        val avatar = holder.getView<AvatarImageView>(R.id.avatar)
        if (item?.owner?.avatar_url.equals("https://gitee.com/assets/no_portrait.png")) {
            avatar.setTextAndColor(item?.owner?.name?.substring(0, 1), R.color.gray)
        }else{
            Glide.with(holder.itemView).load(item?.owner?.avatar_url).into(avatar)
        }
        holder.setText(R.id.name, item?.human_name)
        holder.setText(R.id.desc, item?.description)
        holder.setText(R.id.time, TimeUtils.date2String(TimeUtils.string2Date(item?.updated_at, "yyyy-MM-dd'T'HH:mm:ssXXX"), "yyyy-MM-dd HH:mm:ss"))
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_star, parent)
    }
}