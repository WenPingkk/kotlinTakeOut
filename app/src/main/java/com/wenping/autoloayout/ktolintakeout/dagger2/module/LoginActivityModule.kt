package com.wenping.autoloayout.ktolintakeout.dagger2.module

import com.wenping.autoloayout.ktolintakeout.presenter.LoginActivityPresenter
import com.wenping.autoloayout.ktolintakeout.ui.activity.LoginActivity
import dagger.Module
import dagger.Provides

/**
 * Author WenPing
 * CreateTime 2018/5/1.
 * Description:LoginActivityModule
 */
@Module
class LoginActivityModule(val loginActivity: LoginActivity) {

    @Provides
    fun provideLoginActivityPresenter(): LoginActivityPresenter {
        return LoginActivityPresenter(loginActivity)
    }
}