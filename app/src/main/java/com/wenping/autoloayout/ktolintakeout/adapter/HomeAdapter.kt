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

    //伴生的单利；访问直接通过类.常量名
    companion object {
        val TYPE_TITLE = 0
        val TYPE_SELLER = 1
    }

    //mDatas要给改成可变的，因为在setF方法中实现可变
    var mDatas: ArrayList<String> = ArrayList()

    fun setData(data: ArrayList<String>) {
        this.mDatas = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (mDatas.size > 0) {
            return mDatas.size + 1
        } else {
            return 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_TITLE
        } else {
            return TYPE_SELLER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_TITLE -> return TitleHolder(View.inflate(context, R.layout.item_home_common, null))
            TYPE_SELLER -> return SellerHolder(View.inflate(context, R.layout.item_home_common, null))
            else -> return TitleHolder(View.inflate(context, R.layout.item_home_common, null))
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        val itemViewType = getItemViewType(position)
        when (itemViewType) {
            TYPE_TITLE ->(holder as TitleHolder).bindView("我是头布局")
            TYPE_SELLER->(holder as SellerHolder).bindView(mDatas[position-1])
        }
    }

    inner class SellerHolder(item: View) : RecyclerView.ViewHolder(item) {
        val textView: TextView

        init {
            textView = item.findViewById(R.id.tv)
        }

        fun bindView(data: String) {
            textView.text = data
        }
    }

    inner class TitleHolder(item: View) : RecyclerView.ViewHolder(item) {
        val textView: TextView

        init {
            textView = item.findViewById(R.id.tv)
        }

        fun bindView(data: String) {
            textView.text = data
        }

    }

}