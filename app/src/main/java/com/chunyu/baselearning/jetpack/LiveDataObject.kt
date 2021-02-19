package com.chunyu.baselearning.jetpack

import androidx.lifecycle.MutableLiveData

/*
* 通过单例实现跨页面的通信
* */
object LiveDataObject: MutableLiveData<ExampleModel>()