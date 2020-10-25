package ru.zzemlyanaya.openbagtrecker.main.tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.UserShortView

class LeaderboardRecyclerViewAdapter(private val values: List<UserShortView>)
    : RecyclerView.Adapter<LeaderboardRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_userboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.username
        holder.coins.text = item.coins.toString()
        holder.number.visibility = View.INVISIBLE
        holder.imageMedal.visibility = View.VISIBLE
        when(position) {
            0 -> holder.imageMedal.setImageResource(R.drawable.ic_golden)
            1 -> holder.imageMedal.setImageResource(R.drawable.ic_silver)
            2 -> holder.imageMedal.setImageResource(R.drawable.ic_bronze)
            else -> {
                holder.imageMedal.visibility = View.INVISIBLE
                holder.number.visibility = View.VISIBLE
                holder.number.text = (position+1).toString()
            }
        }
    }

    override fun getItemCount(): Int = values.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val number: TextView = view.findViewById(R.id.textNumber)
        val name: TextView = view.findViewById(R.id.textBoardName)
        val coins: TextView = view.findViewById(R.id.textCoinsBoard)
        val imageMedal: ImageView = view.findViewById(R.id.imageMedal)
    }
}