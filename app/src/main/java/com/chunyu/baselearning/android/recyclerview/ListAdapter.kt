package com.chunyu.baselearning.android.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.item_list.view.*

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class ListAdapter(val dataSource: List<String>): RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = View.inflate(parent.context, R.layout.item_list, null)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val itemView = holder.itemView
        itemView.titleTv.text = dataSource[position]
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}

