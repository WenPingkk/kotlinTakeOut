package com.wenping.autoloayout.ktolintakeout.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.Gson
import com.j256.ormlite.android.AndroidDatabaseConnection
import com.j256.ormlite.dao.Dao
import com.wenping.autoloayout.ktolintakeout.app.GlobalApp
import com.wenping.autoloayout.ktolintakeout.dao.TakeOpenHelper
import com.wenping.autoloayout.ktolintakeout.model.User
import com.wenping.autoloayout.ktolintakeout.ui.activity.LoginActivity
import java.sql.Savepoint

/**
 * Author WenPing
 * CreateTime 2018/5/1.
 * Description:LoginActivityPresenter
 */
class LoginActivityPresenter(val loginActivity: LoginActivity) : NetPresenter() {

    @SuppressLint("LogNotTimber")
    override fun parseJson(json: String?) {
        val gson = Gson()
        val user = gson.fromJson<User>(json, User::class.java)
        if (user != null) {
            GlobalApp.sUser = user

            var connection:AndroidDatabaseConnection?=null
            var startPoint:Savepoint?=null
            try {
                val takeOpenHelper = TakeOpenHelper(loginActivity)
                val userDao: Dao<User, Int> = takeOpenHelper.getDao(User::class.java)
                connection = AndroidDatabaseConnection(takeOpenHelper.writableDatabase,true)
                startPoint = connection.setSavePoint("start")
                connection.isAutoCommit = false//取消自动提交
//                userDao.create(user)
//                userDao.createIfNotExists(user)
//                userDao.update(user)
//                Log.e("dao", "创建新用户或者更新用户信息")
                val userList: List<User> = userDao.queryForAll()
                var isOldUser = false
                userList.forEach {
                    if (it.id == user.id) {
                        isOldUser = true
                    }
                }

                if (isOldUser) {
                    //统计用于的信息；老用户，新用户的数据统计
                    Log.e("dao", "老用户更新信息")
                    userDao.update(user)
                } else {
                    Log.e("dao", "新用户登陆")
                    userDao.create(user)
                }
                connection.commit(startPoint)
                Log.e("dao","事物正常")
                loginActivity.onLoginSuccess(user)
            } catch (e: Exception) {
                Log.e("dao","出现ormlite事务异常${e.localizedMessage}")
                connection?.let {
                    connection.rollback(startPoint)
                }
            }

        } else {
            loginActivity.onLoginFailed()
        }
    }

    fun loginByPhone(phone: String) {
        val loginByPhone = takeService.loginByPhone(phone)
        loginByPhone.enqueue(callback)
    }


}