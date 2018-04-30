package com.wenping.autoloayout.ktolintakeout.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.wenping.autoloayout.ktolintakeout.R
import com.wenping.autoloayout.ktolintakeout.model.Seller
import com.wenping.autoloayout.ktolintakeout.presenter.HomeFragmentPresenter
import kotlinx.android.synthetic.main.item_seller.view.*

/**
 * Author WenPing
 * CreateTime 2018/4/30.
 * Description:
 */
class SellerHolderView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.item_seller, this)
    }

    fun bindView(seller: Seller) {
        tv_title.text = seller.name
        var iconStr = seller.icon
        val indexOf = iconStr!!.indexOf("8080")
        iconStr = HomeFragmentPresenter.Host+iconStr!!.substring(indexOf)
        Log.e("tag",iconStr)
        Picasso
                .with(context)
                .load(iconStr)
                .into(seller_logo)
        ratingBar.rating = seller.score!!.toFloat()
        tv_home_sale.text = "${seller.sale}单"
        tv_home_send_price.text = "￥元${seller.sendPrice}起送/配送费￥${seller.deliveryFee}"
        tv_home_distance.text ="${seller.distance}"

    }
}