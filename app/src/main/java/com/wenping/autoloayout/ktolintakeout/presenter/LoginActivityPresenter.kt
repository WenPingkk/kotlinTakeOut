package com.wenping.autoloayout.ktolintakeout.presenter

import com.google.gson.Gson
import com.wenping.autoloayout.ktolintakeout.app.GlobalApp
import com.wenping.autoloayout.ktolintakeout.model.User
import com.wenping.autoloayout.ktolintakeout.ui.activity.LoginActivity

/**
 * Author WenPing
 * CreateTime 2018/5/1.
 * Description:LoginActivityPresenter
 */
class LoginActivityPresenter(val loginActivity: LoginActivity): NetPresenter() {

    override fun parseJson(json: String?) {
        val gson =Gson()
        val user = gson.fromJson<User>(json, User::class.java)
        if (user != null) {
            loginActivity.onLoginSuccess(user)
            GlobalApp.sUser = user
        } else {
            loginActivity.onLoginFailed()
        }
    }

    fun loginByPhone(phone: String) {
        val loginByPhone = takeService.loginByPhone(phone)
        loginByPhone.enqueue(callback)
    }



}