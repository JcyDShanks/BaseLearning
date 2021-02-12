package com.chunyu.baselearning.android.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.RecyclerView
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_list.*

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initViews()
    }

    private fun initViews() {
        val list = arrayListOf<String>()
        for (i in 1..200) {
            list.add("item $i")
        }
        val adapter = ListAdapter(list)

        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@RecyclerViewActivity).apply { orientation = VERTICAL }
            this.adapter = adapter
        }

        adapter.notifyDataSetChanged()
    }
}