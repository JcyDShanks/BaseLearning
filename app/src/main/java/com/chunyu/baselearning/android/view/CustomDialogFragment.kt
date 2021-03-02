package com.chunyu.baselearning.android.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.chunyu.baselearning.R

class CustomDialogFragment: DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_choose_printer, null)
    }

    override fun onResume() {
        dialog?.window?.run {
            val params = attributes
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            params.gravity = Gravity.CENTER
            attributes = params as WindowManager.LayoutParams
        }
        dialog?.run {
            setCancelable(true)
            setCanceledOnTouchOutside(false)
        }
        super.onResume()
    }
}