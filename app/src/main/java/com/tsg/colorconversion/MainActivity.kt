package com.tsg.colorconversion

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    val adapter = ColorAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()

        btn_show.setOnClickListener {
            updateList()
            hideKeyboard(this)
        }
    }

    fun initList() {
        rv_colors.layoutManager = LinearLayoutManager(this)
        adapter.data = getColors()
        rv_colors.adapter = adapter
    }

    fun updateList() {
        try {
            val color = Color.parseColor(et_color.text.toString())
            adapter.data = getColors(color)
            adapter.notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getColors(color: Int = resources.getColor(R.color.colorPrimary)): List<ColorPojo> {
        val colorList = ArrayList<ColorPojo>()

        val f = FloatArray(3)
        ColorUtils.colorToHSL(color, f)
        Log.d("Trent", "[${f[0]},${f[1]},${f[2]}]")

        for (i in 100 downTo 1) {
            val newHsl = FloatArray(3)
            newHsl[0] = f[0]
            newHsl[1] = f[1]
            newHsl[2] = i / 100f
            val showPercent = "$i%"
            val colorInt = ColorUtils.HSLToColor(newHsl)
            val name = "#" + Integer.toHexString(colorInt).substring(2)
            val decimal = BigDecimal(f[2].toDouble())
            val selected = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toFloat() == newHsl[2]

            val pojo = ColorPojo(showPercent, colorInt, name, selected)
            Log.d("Trent", "pojo = $pojo")
            colorList.add(pojo)
        }

        return colorList
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}
