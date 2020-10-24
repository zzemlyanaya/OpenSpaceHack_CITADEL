package ru.zzemlyanaya.openbagtrecker.main.editprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.Device

class DevicesRecyclerViewAdapter(
    private var values: ArrayList<Device>
) : RecyclerView.Adapter<DevicesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_device, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.textModel.text = item.model
        holder.textOS.text = item.os
    }

    override fun getItemCount(): Int = values.size

    fun getData() = values

    fun setData(list: List<Device>){
        values.clear()
        values.addAll(list)
    }

    fun removeItem(position: Int){
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: Device, position: Int) {
        values.add(position, item)
        notifyItemInserted(position)
    }

    fun addItem(item: Device) {
        values.add(item)
        notifyItemInserted(values.size - 1)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textModel: TextView = view.findViewById(R.id.textDevice)
        val textOS: TextView = view.findViewById(R.id.textDeviceOS)
    }
}