package com.wenping.autoloayout.ktolintakeout.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wenping.autoloayout.ktolintakeout.R

/**
 * @author WenPing
 * @date 2018/4/28
 * @decription:
 *<p>
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = View.inflate(activity, R.layout.fragment_home, null)

        return view
    }
}