package com.wenping.autoloayout.ktolintakeout.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.wenping.autoloayout.ktolintakeout.R
import kotlinx.android.synthetic.main.item_title.view.*

/**
 * Author WenPing
 * CreateTime 2018/4/30.
 * Description:
 */
class TitleHolderView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        val inflate = View.inflate(context, R.layout.item_title, this)
    }

    val urlMaps = HashMap<String, String>()
    fun bindView(data: String) {
        if (urlMaps.size == 0) {
            urlMaps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg")
            urlMaps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png")
            urlMaps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg")
            urlMaps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg")

            for (urlMap in urlMaps) {
                val textSliderView = TextSliderView(context)
                textSliderView.description(urlMap.key).image(urlMap.value)
                slider.addSlider(textSliderView)
            }
        }
    }

}