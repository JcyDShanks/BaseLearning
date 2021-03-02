package com.chunyu.baselearning.android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        dialogBtn.setOnClickListener {
            initDialog()
        }

        dialogFragmentBtn.setOnClickListener {
            initDialogFragment()
        }
    }

    fun initDialog() {
        AlertDialog.Builder(this)
            .setTitle("标题")
            .setPositiveButton("取消") { dialog, which ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    fun initDialogFragment() {
        CustomDialogFragment().show(this.supportFragmentManager, "Custom")
    }
}