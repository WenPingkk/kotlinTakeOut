package com.wenping.autoloayout.ktolintakeout.dagger2.module

import com.wenping.autoloayout.ktolintakeout.presenter.HomeFragmentPresenter
import com.wenping.autoloayout.ktolintakeout.ui.fragment.HomeFragment
import dagger.Module
import dagger.Provides

/**
 * Author WenPing
 * CreateTime 2018/4/30.
 * Description:
 */
@Module
class HomeFragmentModule(val homeFragment: HomeFragment) {

    @Provides
    fun provideHomeFragmentPresenter():HomeFragmentPresenter{
        return HomeFragmentPresenter(homeFragment)
    }

}