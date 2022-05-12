package com.eddieshin.shoppinglist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eddieshin.shoppinglist.data.db.entities.ShoppingItem
import com.eddieshin.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(private val viewModel: ShoppingViewModel, var items: List<ShoppingItem>) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    companion object {
        private const val TAG = "ItemsAdapter"
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false))
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder")

        val item = items[position]

        val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)
        val tvAmount = holder.itemView.findViewById<TextView>(R.id.tvAmount)

        tvName.text = item.name
        tvAmount.text = item.amount.toString()

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(item)
        }

        holder.itemView.ivPlus.setOnClickListener {
            item.amount++
            viewModel.upsert(item)

        }

        holder.itemView.ivMinus.setOnClickListener {
            item.amount--
            viewModel.upsert(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}