package com.chunyu.baselearning.reflact

import android.view.View

class MyProxyListener: View.OnClickListener {
    override fun onClick(v: View?) {
        print("V")
    }
}