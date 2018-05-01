package com.wenping.autoloayout.ktolintakeout.dagger2.component

import com.wenping.autoloayout.ktolintakeout.dagger2.module.LoginActivityModule
import com.wenping.autoloayout.ktolintakeout.ui.activity.LoginActivity
import dagger.Component

/**
 * Author WenPing
 * CreateTime 2018/5/1.
 * Description:
 */
@Component(modules = arrayOf(LoginActivityModule::class))
interface LoginActivityComponent {
    fun inject(loginactivity: LoginActivity)
}
