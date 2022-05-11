package com.eddieshin.shoppinglist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(val context: Context, val items: List<ShoppingItem>) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    companion object {
        private const val TAG = "ItemsAdapter"
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(LayoutInflater.from(context).inflate(R.layout.shopping_item, parent, false))
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder")

        val item = items[position]

        val tvName = holder.itemView.tvName

        val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)
        val tvAmount = holder.itemView.findViewById<TextView>(R.id.tvAmount)

        tvName.text = item.name
        tvAmount.text = item.amount.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}