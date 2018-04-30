package com.wenping.autoloayout.ktolintakeout.ui.fragment

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wenping.autoloayout.ktolintakeout.R
import com.wenping.autoloayout.ktolintakeout.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author WenPing
 * @date 2018/4/28
 * @decription:
 *<p>
 */
class HomeFragment : Fragment() {

    var data: ArrayList<String> = ArrayList()
    var sum = 0
    var distance = 0
    var alpha = 0
    val homeAdapter by lazy {
        HomeAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View = View.inflate(activity, R.layout.fragment_home, null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_home.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
        }
        distance = 120.dp2px()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }


    private fun initData() {
        for (i in 0 until 100) {
            data.add("我是商家" + i)
        }
        homeAdapter.setData(data)

        rv_home.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                sum += dy
//                Log.e("Tag",sum.toString())

                if (sum > distance) {
                    alpha = 255
                } else {
                    alpha = 55+200*sum/distance
                    ll_title_container.setBackgroundColor(
                            Color.argb(
                                    alpha,0x31,0x90,0xe8
                            )
                    )
                }

            }
        })
    }

    /**
     * //这是kotlin的拓展函数
     * 把转化功能添加到Int类中作为扩展函数
     */
    fun Int.dp2px(): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                toFloat(), resources.displayMetrics).toInt()

    }

}