package com.wenping.autoloayout.ktolintakeout.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.wenping.autoloayout.ktolintakeout.R

/**
 * Author WenPing
 * CreateTime 2018/4/30.
 * Description:
 */
class SellerHolderView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.item_seller, this)
    }

    fun bindView(get: String) {


    }
}