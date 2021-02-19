package com.chunyu.baselearning.android.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.Scroller
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.item_home_button.view.*
import kotlin.math.abs


class SlideLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private lateinit var contentView : View
    private lateinit var menuView : View

    // 测量位置
    private var contentWidth: Int  = 0
    private var contentHeight: Int  = 0
    private var menuWidth: Int  = 0
    private var menuHeight: Int  = 0

    private var scroller: Scroller = Scroller(context)
    private var downX: Float = 0f
    private var downY: Float = 0f
    private var endX: Float = 0f
    private var endY: Float = 0f
    private var startX: Float = 0f
    private var startY: Float = 0f


    // 布局加载完成后的回调生命周期
    override fun onFinishInflate() {
        super.onFinishInflate()
        contentView = findViewById(R.id.contentLayout)
        menuView = findViewById(R.id.menuLayout)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        contentHeight = contentView.measuredHeight
        contentWidth = contentView.measuredWidth
        menuHeight = menuView.measuredHeight
        menuWidth = menuView.measuredWidth
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        menuView.layout(contentWidth, 0, contentWidth + menuWidth, menuHeight)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                downX = event.x
                downY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                endX = event.x
                endY = event.y
                // 横向滑动距离
                val horizontalMoveDistance = abs(endX - downX)
                // 发生横行滑动时禁用纵向滑动
                if (horizontalMoveDistance > 0) {
                    parent.requestDisallowInterceptTouchEvent(true)
                }
                var distance = endX - startX
                // 向左滑为负值，通过下面一行代码转换至滑至的x坐标
                distance = scrollX - distance
                distance = if (distance < 0) {
                    0f
                } else {
                    menuWidth.toFloat()
                }
                scrollTo(distance.toInt(), 0)

                // 更新开始x坐标
                startX = event.x
            }
            MotionEvent.ACTION_UP -> {
                val totalX = scrollX
                if (totalX < menuWidth / 2) {
                    closeMenu()
                } else {
                    openMenu()
                }
            }

        }
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        var isIntercept = false // 最初为不拦截
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x
                downX = startX
                try {
                    if (onStateChangeListener == null) {
                        throw Exception("状态改变监听者未设置")
                    }
                    onStateChangeListener?.onDown(this@SlideLayout) // 关闭其他item
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                endX = ev.x
                val distance = abs(endX - startX)
                // 横向发生滑动，拦截事件
                if (distance > 0) {
                    isIntercept = true
                }
            }
        }
        return isIntercept

    }

    override fun computeScroll() {
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            // 滑动未结束
            scrollTo(scroller.currX, scroller.currY)
            invalidate()
        }
    }

    fun closeMenu() {
        val distance = 0 - scrollX
        scroller.startScroll(scrollX, scrollY, distance, 0)
        invalidate()
        try {
            if (onStateChangeListener == null) {
                throw java.lang.Exception("状态改变监听者未设置")
            }
            onStateChangeListener!!.onClose(this)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun openMenu() {
        val distance = menuWidth - scrollX // 剩余未滑动距离
        scroller.startScroll(scrollX, scrollY, distance, 0) // 滑动处理
        invalidate() // 重绘
        try {
            if (onStateChangeListener == null) {
                throw java.lang.Exception("状态改变监听者未设置")
            }
            onStateChangeListener!!.onOpen(this) // 打开item为当前item
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    interface OnStateChangeListener {
        fun onClose(layout: SlideLayout)
        fun onDown(layout: SlideLayout)
        fun onOpen(layout: SlideLayout)
    }

    var onStateChangeListener: OnStateChangeListener? = null
}

