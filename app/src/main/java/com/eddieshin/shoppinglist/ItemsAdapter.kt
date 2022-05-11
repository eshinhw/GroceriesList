package com.eddieshin.shoppinglist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(val context: Context, val items: List<ShoppingItem>) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    companion object {
        private const val TAG = "ItemsAdapter"
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder")

        val item = items[position]

        val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)
        val tvAmount = holder.itemView.findViewById<TextView>(R.id.tvAmount)

        tvName.text = item.name
        tvAmount.text = item.amount.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}