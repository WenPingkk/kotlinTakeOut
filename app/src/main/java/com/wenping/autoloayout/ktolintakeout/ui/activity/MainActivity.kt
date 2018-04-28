package com.wenping.ktolintakeout

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.wenping.autoloayout.ktolintakeout.R
import com.wenping.autoloayout.ktolintakeout.ui.fragment.HomeFragment
import com.wenping.autoloayout.ktolintakeout.ui.fragment.MoreFragment
import com.wenping.autoloayout.ktolintakeout.ui.fragment.OrderFragment
import com.wenping.autoloayout.ktolintakeout.ui.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomBar()

    }

    val fragments: List<Fragment> = listOf<Fragment>(HomeFragment(),OrderFragment(),UserFragment(),MoreFragment())

    private fun initBottomBar() {

        for (i in 0 until main_bottom_bar.childCount) {
            main_bottom_bar.getChildAt(i).setOnClickListener {
                changeIndex(i)
            }
        }
    }

    private fun changeIndex(index: Int) {
        for (i in 0 until main_bottom_bar.childCount) {
            val child = main_bottom_bar.getChildAt(i)
            if (i == index) {
                //选中,则禁用效果
                setEnable(child,false)
            } else {
                //没选中,则不禁用
                setEnable(child,true)
            }
        }
        fragmentManager.beginTransaction().replace(R.id.main_content,fragments[index]).commit()
    }

    private fun setEnable(child: View, isEnable: Boolean) {
        child.isEnabled = isEnable
        if (child is ViewGroup) {
            for (i in 0 until child.childCount) {
                child.getChildAt(i).isEnabled = isEnable
            }
        }
    }
}
