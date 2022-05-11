package com.eddieshin.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eddieshin.shoppinglist.R
import com.eddieshin.shoppinglist.ShoppingItemAdapter
import com.eddieshin.shoppinglist.data.db.ShoppingDatabase
import com.eddieshin.shoppinglist.data.db.entities.ShoppingItem
import com.eddieshin.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val items = createSampleItems()
        val adapter = ShoppingItemAdapter(this, items)

        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapter
    }

    private fun createSampleItems() : List<ShoppingItem> {
        val sampleItems : MutableList<ShoppingItem> = mutableListOf()
        for (i in 1..100) {
            val item = ShoppingItem("item${i}", i)
            sampleItems.add(item)
        }
        return sampleItems
    }
}