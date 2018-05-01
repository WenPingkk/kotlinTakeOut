package com.wenping.autoloayout.ktolintakeout.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wenping.autoloayout.ktolintakeout.model.Seller
import com.wenping.autoloayout.ktolintakeout.ui.fragment.HomeFragment
import org.json.JSONObject

/**
 * Author WenPing
 * CreateTime 2018/4/30.
 * Description:
 */
class HomeFragmentPresenter(val homeFragment: HomeFragment) : NetPresenter() {

    fun getHomeInfo() {
        //TODO 异步访问
        val homeInfoCall = takeService.getHomeInfo()
        homeInfoCall.enqueue(callback)
    }

    override fun parseJson(json: String?) {

        val gson = Gson()
        var jsonObject = JSONObject(json)
        val nearby = jsonObject.getString("nearbySellerList")

//        gson.fromJson(nearby, object : TypeToken<List<Seller>>() {}.type)
        val nearbySellers: List<Seller> = gson.fromJson<List<Seller>>(nearby, object : TypeToken<List<Seller>>() {}.type)
        Log.e("tag", nearby)


        val other = jsonObject.getString("otherSellerList")
        val otherSellers: List<Seller> = gson.fromJson<List<Seller>>(other, object : TypeToken<List<Seller>>() {}.type)
        Log.e("tag", other)

//        TODO 刷新
        if (nearbySellers.isNotEmpty() || otherSellers.isNotEmpty()) {
            homeFragment.onHomeSuccess(nearbySellers,otherSellers)
        } else {
            homeFragment.onHomeFailed()
        }
    }
}