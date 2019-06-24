package com.tsg.colorconversion

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.color_layout.view.*

class ColorAdapter(val context: Context) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    var data: List<ColorPojo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.color_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: ColorPojo? = data?.get(position)

        holder.tv_percent.text = data?.percent
        holder.tv_color.setBackgroundColor(data?.color!!)
        holder.tv_name.text = data.text

        if (data.selected) {
            holder.tv_percent.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            holder.tv_percent.setTextColor(context.resources.getColor(R.color.colorAccent))
            holder.tv_name.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        } else {
            holder.tv_percent.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
            holder.tv_percent.setTextColor(context.resources.getColor(android.R.color.black))
            holder.tv_name.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_percent: TextView = view.tv_percent
        val tv_color: TextView = view.tv_color
        val tv_name: TextView = view.tv_name
    }
}