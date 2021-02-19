package com.chunyu.baselearning.android.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


class SlidingButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    lateinit var contentView: View
    lateinit var rightView: View

    fun addContentView(view: View) {
        contentView = view
        contentView.tag = "contentView"

        val cv: View? = findViewWithTag("contentView")
        cv?.let {
            this.removeView(it)
        }

        val layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        this.addView(this.contentView, layoutParams);
    }

    fun addRightView(view: View) {
        rightView = view
        val mv: View? = findViewWithTag("rightView")
        if (mv != null) {
            removeView(mv)
        }
        val layoutParams = LayoutParams(
//            mRightCanSlide,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        this.addView(this.rightView, layoutParams)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_MOVE -> {
                
            }
            MotionEvent.ACTION_CANCEL -> {

            }
        }
        return super.onInterceptTouchEvent(ev)
    }

}