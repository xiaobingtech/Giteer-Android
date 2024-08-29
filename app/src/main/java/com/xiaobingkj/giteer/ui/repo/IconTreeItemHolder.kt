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

package com.xiaobingkj.giteer.ui.repo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.github.johnkil.print.PrintView
import com.unnamed.b.atv.model.TreeNode
import io.github.rosemoe.sora.app.R

class IconTreeItemHolder(context: Context?) : TreeNode.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem>(context){

    private var tvValue: TextView? = null
    private var arrowView: PrintView? = null

    class IconTreeItem(val icon: Int, val name: String, val path: String, val downloadURL: String, val sha: String) {

    }

    override fun createNodeView(node: TreeNode?, value: IconTreeItem?): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_icon_node, null, false)
        tvValue = view.findViewById<TextView>(R.id.node_value)
        tvValue?.text = value?.name
        val iconView = view.findViewById<PrintView>(R.id.icon)
        iconView.iconText = context.resources.getString(value?.icon!!)
        arrowView = view.findViewById<PrintView>(R.id.arrow_icon)
        if (value.icon.equals(R.string.ic_drive_file)) {
            arrowView?.visibility = View.GONE
        }
        return view
    }

    override fun toggle(active: Boolean) {
        super.toggle(active)
        arrowView?.iconText = context.resources.getString(if (active) {R.string.ic_keyboard_arrow_down} else {R.string.ic_keyboard_arrow_right})
    }
}