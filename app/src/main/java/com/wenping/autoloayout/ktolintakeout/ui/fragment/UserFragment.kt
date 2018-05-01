package com.wenping.autoloayout.ktolintakeout.ui.fragment

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wenping.autoloayout.ktolintakeout.R
import com.wenping.autoloayout.ktolintakeout.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_user.*

/**
 * @author WenPing
 * @date 2018/4/28
 * @decription:UserFragment
 *<p>
 */
class UserFragment : Fragment() {


    override fun onStart() {
        super.onStart()
        
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return View.inflate(activity, R.layout.fragment_user, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val intent = Intent(activity, LoginActivity::class.java)
        login.setOnClickListener{
            activity.startActivity(intent)
        }
    }
}