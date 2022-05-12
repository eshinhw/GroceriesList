package com.eddieshin.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eddieshin.shoppinglist.AddDialogListener
import com.eddieshin.shoppinglist.AddShoppingItemDialog
import com.eddieshin.shoppinglist.R
import com.eddieshin.shoppinglist.ShoppingItemAdapter
import com.eddieshin.shoppinglist.data.db.ShoppingDatabase
import com.eddieshin.shoppinglist.data.db.entities.ShoppingItem
import com.eddieshin.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val items = createSampleItems()
        val adapter = ShoppingItemAdapter(viewModel, items)

        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fabAdd.setOnClickListener{
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
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