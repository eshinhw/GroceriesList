package com.eddieshin.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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