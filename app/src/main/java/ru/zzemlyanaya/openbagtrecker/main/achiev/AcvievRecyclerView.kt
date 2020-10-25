package ru.zzemlyanaya.openbagtrecker.main.achiev

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.Achievement

class AchievRecyclerViewAdapter(private val values: List<Achievement>)
    : RecyclerView.Adapter<AchievRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_achievement, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.name
        if(item.lvl >= 1)
            holder.star1.alpha = 1f
        if(item.lvl >= 2)
            holder.star2.alpha = 1f
        if(item.lvl >= 3)
            holder.star3.alpha = 1f
    }

    override fun getItemCount(): Int = values.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.textAchievName)
        val star1: ImageView = view.findViewById(R.id.star)
        val star2: ImageView = view.findViewById(R.id.star2)
        val star3: ImageView = view.findViewById(R.id.star3)
    }
}
