package com.chunyu.baselearning

import android.app.Activity
import android.app.ListActivity
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chunyu.baselearning.android.HandlerActivity
import com.chunyu.baselearning.android.NextActivity
import com.chunyu.baselearning.android.binder.MyClientActivity
import com.chunyu.baselearning.android.recyclerview.RecyclerViewActivity
import com.chunyu.baselearning.java.SynchronizedTestActivity
import com.chunyu.baselearning.models.BinderAction
import com.chunyu.baselearning.models.HomeActionModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_home_button.view.*
import org.greenrobot.eventbus.Subscribe
import xiaofei.library.hermeseventbus.HermesEventBus
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private lateinit var actionList: ArrayList<HomeActionModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAction()
        recyclerView.adapter = object : RecyclerView.Adapter<HomeViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): HomeViewHolder {
                val itemView = View.inflate(parent.context, R.layout.item_home_button, null).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                }
                return HomeViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
                val itemView = holder.view
                itemView.button.text = actionList[position].desc
                itemView.button.setOnClickListener(actionList[position].listener)
            }

            override fun getItemCount(): Int {
                return actionList.size
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun initAction() {
        actionList = arrayListOf()
        actionList.add(HomeActionModel(BinderAction, "Binder通信", View.OnClickListener {
            val intent = Intent(this@MainActivity, MyClientActivity::class.java)
            startActivity(intent)
        }))

        for (i in 0..100) {
            actionList.add(HomeActionModel(BinderAction, "Binder通信", View.OnClickListener {
                val intent = Intent(this@MainActivity, MyClientActivity::class.java)
                startActivity(intent)
            }))
        }
    }
}

data class HomeViewHolder(val view: View): RecyclerView.ViewHolder(view)