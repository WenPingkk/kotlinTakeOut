package com.wenping.autoloayout.ktolintakeout.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.wenping.autoloayout.ktolintakeout.ui.widget.SellerHolderView
import com.wenping.autoloayout.ktolintakeout.ui.widget.TitleHolderView

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
            return 1
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
            TYPE_TITLE -> return TitleHolder(TitleHolderView(context))
            TYPE_SELLER -> return SellerHolder(SellerHolderView(context))
            else -> return return TitleHolder(TitleHolderView(context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemViewType = getItemViewType(position)
        when (itemViewType) {
            TYPE_TITLE -> {
                val titleHolderView = holder.itemView as TitleHolderView
                titleHolderView.bindView(mDatas.get(0))
            }
            TYPE_SELLER ->{
                val sellerHolderView = holder.itemView as SellerHolderView
                sellerHolderView.bindView(mDatas.get(position - 1))
            }
        }
    }

    inner class SellerHolder(item: View) : RecyclerView.ViewHolder(item) {}
    inner class TitleHolder(item: View) : RecyclerView.ViewHolder(item) {}

}