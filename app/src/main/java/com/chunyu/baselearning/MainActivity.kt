package com.chunyu.baselearning

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chunyu.baselearning.android.binder.MyClientActivity
import com.chunyu.baselearning.android.launchMode.ALaunchActivity
import com.chunyu.baselearning.android.uitools.StatusBarTools
import com.chunyu.baselearning.models.BinderAction
import com.chunyu.baselearning.models.HomeActionModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_home_button.view.*

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
        StatusBarTools.setStatusBarLightMode(this)
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
                itemView.contentLayout.text = actionList[position].desc
                itemView.contentLayout.setOnClickListener(actionList[position].listener)
            }

            override fun getItemCount(): Int {
                return actionList.size
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            getDrawable(R.drawable.divider_home)?.let { decoration.setDrawable(it) }
        }
        recyclerView.addItemDecoration(decoration)
        recyclerView.adapter?.notifyDataSetChanged()
        Log.e("chunyu Main", "onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("chunyu Main", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.e("chunyu Main", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("chunyu Main", "onResume")
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.e("chunyu Main", "onPostResume")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("chunyu Main", "onNewIntent")
    }

    override fun onPause() {
        super.onPause()
        Log.e("chunyu Main", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("chunyu Main", "onStop")
    }

    private fun initAction() {
        actionList = arrayListOf()
        actionList.addAll(PageRouter.router(this))
    }
}

data class HomeViewHolder(val view: View): RecyclerView.ViewHolder(view)