package com.wenping.autoloayout.ktolintakeout.dagger2.component

import com.wenping.autoloayout.ktolintakeout.dagger2.module.HomeFragmentModule
import com.wenping.autoloayout.ktolintakeout.ui.fragment.HomeFragment
import dagger.Component

/**
 * Author WenPing
 * CreateTime 2018/4/30.
 * Description:
 */
@Component(modules = arrayOf(HomeFragmentModule::class))
interface HomeFragmentComponent {

    fun inject(homeFragment: HomeFragment)
}