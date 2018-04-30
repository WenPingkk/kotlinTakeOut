package com.wenping.autoloayout.ktolintakeout.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wenping.autoloayout.ktolintakeout.R
import com.wenping.autoloayout.ktolintakeout.adapter.HomeAdapter
import org.jetbrains.anko.find

/**
 * @author WenPing
 * @date 2018/4/28
 * @decription:
 *<p>
 */
class HomeFragment : Fragment() {

    lateinit var homeAdapter:HomeAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = View.inflate(activity, R.layout.fragment_home, null)
        val rvHome = view.find<RecyclerView>(R.id.rv_home)
        homeAdapter = HomeAdapter(activity)
        rvHome.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    var data: ArrayList<String> = ArrayList()

    private fun initData() {
        for (i in 0 until 100) {
            data.add("我是商家" + i)
        }
        homeAdapter.setData(data)
    }
}