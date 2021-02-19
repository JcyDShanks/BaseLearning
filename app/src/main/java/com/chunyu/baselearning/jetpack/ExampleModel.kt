package com.chunyu.baselearning.jetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExampleModel: ViewModel() {
    val rawValue: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


}