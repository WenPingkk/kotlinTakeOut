package com.wenping.autoloayout.ktolintakeout.http

import retrofit2.Call
import retrofit2.http.GET

/**
 * Author WenPing
 * CreateTime 2018/4/30.
 * Description:
 */
interface TakeService {

    @GET("home")
    fun getHomeInfo():Call<ResponseInfo>
}