package com.wenping.autoloayout.ktolintakeout.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wenping.autoloayout.ktolintakeout.http.ResponseInfo
import com.wenping.autoloayout.ktolintakeout.http.TakeService
import com.wenping.autoloayout.ktolintakeout.model.Seller
import com.wenping.autoloayout.ktolintakeout.ui.fragment.HomeFragment
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author WenPing
 * CreateTime 2018/4/30.
 * Description:
 */
class HomeFragmentPresenter(val homeFragment: HomeFragment) {

    val takeService: TakeService

    init {

        val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.42.193:8080/TakeoutService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        takeService = retrofit.create<TakeService>(TakeService::class.java)
    }


    fun getHomeInfo() {
        //TODO 异步访问
        val homeInfoCall = takeService.getHomeInfo()
        homeInfoCall.enqueue(object : Callback<ResponseInfo> {
            override fun onFailure(call: Call<ResponseInfo>?, t: Throwable?) {
                Log.e("tag", "没连上服务器")
            }

            override fun onResponse(call: Call<ResponseInfo>?, response: Response<ResponseInfo>?) {

                if (response == null) {

                } else {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body!!.code.equals("0")) {
                            //请求成功的code
                            val json = body.data

                            parseJson(json)
                        } else if (body!!.code.equals("-1")) {
                            //根据文档具体分析
                        }
                    } else {
                        Log.e("tag", "服务器代码出戳了")
                    }
                }
            }

        })
    }

    private fun parseJson(json: String?) {

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