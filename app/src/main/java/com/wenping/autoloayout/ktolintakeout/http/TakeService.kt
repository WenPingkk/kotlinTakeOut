package com.wenping.autoloayout.ktolintakeout.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author WenPing
 * CreateTime 2018/4/30.
 * Description:
 */
interface TakeService {

    @GET("home")
    fun getHomeInfo(): Call<ResponseInfo>

    @GET("login")
    fun loginByPhone(@Query("phone") phone:String):Call<ResponseInfo>


}