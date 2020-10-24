package ru.zzemlyanaya.openbagtrecker.main.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.Stuff


class StuffRecyclerViewAdapter(
    private val values: List<Stuff>
) : RecyclerView.Adapter<StuffRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stuff, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.textCost.text = item.cost.toString()
        holder.image.setImageResource(item.imageId)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textCost: TextView = view.findViewById(R.id.textStuffCost)
        val image: ImageView = view.findViewById(R.id.imageStuff)
    }
}