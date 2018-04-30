package com.wenping.autoloayout.ktolintakeout.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wenping.autoloayout.ktolintakeout.R

/**
 * Author WenPing
 * CreateTime 2018/4/30.
 * Description:
 */
class HomeAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //mDatas要给改成可变的，因为在setF方法中实现可变
    var mDatas: ArrayList<String> = ArrayList()

    fun setData(data: ArrayList<String>) {
        this.mDatas = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mDatas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        val homeItemViewholder = holder as HomeItemViewholder
        homeItemViewholder.bindView(mDatas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val itemView = View.inflate(context, R.layout.item_home_common, null)
        return HomeItemViewholder(itemView)
    }

    inner class HomeItemViewholder(item:View) : RecyclerView.ViewHolder(item) {
        val textView:TextView
        init {
            textView = item.findViewById(R.id.tv)
        }

        fun bindView(data: String) {
            textView.text = data
        }

    }
}