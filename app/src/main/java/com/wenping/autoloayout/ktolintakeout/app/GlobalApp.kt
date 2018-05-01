package com.wenping.autoloayout.ktolintakeout.app

import com.mob.MobApplication
import com.wenping.autoloayout.ktolintakeout.model.User

/**
 * @Author WenPing
 * @CreateTime 2018/5/1.
 * @Description:GlobalApp
 */
class GlobalApp : MobApplication() {

    companion object {
        var sUser = User()
    }

    override fun onCreate() {
        super.onCreate()

        //-1表示未登录
        sUser.id = -1

    }

}