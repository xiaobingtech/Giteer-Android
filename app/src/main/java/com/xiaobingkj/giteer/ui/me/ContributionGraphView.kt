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

package com.xiaobingkj.giteer.ui.me

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.xiaobingkj.giteer.data.model.ContributionBean
import java.time.DayOfWeek
import java.time.LocalDate

class ContributionGraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // 方块大小和间距
    private val squareSize = 40f // 方块的大小
    private val squareMargin = 10f // 方块之间的间距

    // 颜色数组，从少到多的贡献对应的颜色
    private val colors = arrayOf(
        0xFFEBEDF0.toInt(), // 最少贡献的颜色
        0xFF9BE9A8.toInt(),
        0xFF40C463.toInt(),
        0xFF30A14E.toInt(),
        0xFF216E39.toInt() // 最多贡献的颜色
    )

    var contributions: List<ContributionBean.DataDTO.ContributionCalendarDTO.YearStreakDTO> = emptyList()
        set(value) {
            field = value
            // 当数据更新时，调用invalidate()来请求重绘视图
            invalidate()
        }

    // 模拟的贡献数据，实际开发中应从外部获取
//    private val contributions = List(52 * 7) { (0..4).random() }

    private val paint = Paint().apply {
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 确定起始日期
        val startDate = LocalDate.parse(contributions.minByOrNull { it.date }?.date ?: return)
        val endDate = LocalDate.parse(contributions.maxByOrNull { it.date }?.date ?: return)

        var currentDate = startDate
        var col = 0

        while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
            val contribution = contributions.find { LocalDate.parse(it.date).isEqual(currentDate) }

            if (contribution != null) {
                val row = currentDate.dayOfWeek.value - 1 // 使其从0开始，匹配数组索引
                val x = col * (squareSize + squareMargin)
                val y = row * (squareSize + squareMargin)

                paint.color = when (contribution.classX) {
                    "less" -> Color.LTGRAY
                    else -> Color.GREEN // 根据实际情况调整
                }
                canvas?.drawRect(x, y, x + squareSize, y + squareSize, paint)
            }

            if (currentDate.dayOfWeek == DayOfWeek.SUNDAY) col++
            currentDate = currentDate.plusDays(1)
        }
    }

    // 根据内容计算视图的大小，这里简化处理
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = (squareSize + squareMargin) * 54 + squareMargin
        val desiredHeight = (squareSize + squareMargin) * 7 + squareMargin
        setMeasuredDimension(desiredWidth.toInt(), desiredHeight.toInt())
    }
}