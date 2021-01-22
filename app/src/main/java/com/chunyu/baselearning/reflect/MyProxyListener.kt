package com.chunyu.baselearning.reflect

import android.view.View

class MyProxyListener: View.OnClickListener {
    override fun onClick(v: View?) {
        print("V")
    }
}