package com.chunyu.baselearning.viewProblem

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.view_item_calling.view.*

class CallingItemView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        View.inflate(context, R.layout.view_item_calling, this)
        setOnLongClickListener {
            visibleUpdateDelete()
            true
        }
        deleteCl.setOnClickListener {
            visibleUpdateDelete()
        }
    }

    private fun visibleUpdateDelete() {
        if (this.deleteCl.visibility == VISIBLE) {
            this.deleteCl?.visibility = GONE
        } else {
            this.deleteCl?.visibility = VISIBLE
        }
    }
}