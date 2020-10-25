package ru.zzemlyanaya.openbagtrecker.main.tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.Bug
import kotlin.random.Random

class BugsRecyclerViewAdapter(private val values: List<Bug>)
    : RecyclerView.Adapter<BugsRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bug, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.authorName.text = item.authorName
        holder.bugName.text = item.name
        holder.date.text = item.date
        holder.tag.text = item.typeBug
        if (Random.nextInt() % 2 == 0)
            holder.authorImage.setImageResource(R.drawable.ic_wizard)
        else
            holder.authorImage.setImageResource(R.drawable.ic_pirate)
    }

    override fun getItemCount(): Int = values.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val authorName: TextView = view.findViewById(R.id.textBugAuthor)
        val date: TextView = view.findViewById(R.id.textDate)
        val bugName: TextView = view.findViewById(R.id.textBugName)
        val authorImage: ImageView = view.findViewById(R.id.bugAuthorImage)
        val tag: Chip = view.findViewById(R.id.chipBugType)
    }
}