package com.wenping.autoloayout.ktolintakeout.presenter

import android.util.Log
import com.wenping.autoloayout.ktolintakeout.http.ResponseInfo
import com.wenping.autoloayout.ktolintakeout.http.TakeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author WenPing
 * CreateTime 2018/5/1.
 * Description:
 */
abstract class NetPresenter {

    protected val Tag = javaClass.simpleName

    val takeService: TakeService
    companion object {
        //        val Host = "http://192.168.31.12:"
        val Host = "http://192.168.42.32:"
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Host + "8080/TakeoutService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        takeService = retrofit.create<TakeService>(TakeService::class.java)
    }

    val callback = object : Callback<ResponseInfo> {
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

    }

    abstract fun parseJson(json: String?)
}