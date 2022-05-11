package com.eddieshin.shoppinglist

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    private fun showAlertDialog() {
        val mapFormView = LayoutInflater.from(this).inflate(R.layout.dialog_add_shopping_item, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Map Title")
            .setView(mapFormView)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("OK", null)
            .show()

        // we only want to add a market on the map when the user taps on OK button on dialog
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            // when working with user input data, we need to make sure they are valid.
            // error validation & form handling
            val title = mapFormView.findViewById<EditText>(R.id.etMapTitleName).text.toString()

            if (title.trim().isNotEmpty()) {
                // User has provided proper title
                val intent = Intent(this@MainActivity, CreateMapActivity::class.java)
                intent.putExtra(EXTRA_MAP_TITLE, title)
                startActivityForResult(intent, REQUEST_CODE)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Map must have non-empty title!", Toast.LENGTH_LONG).show()
                dialog.dismiss()
                return@setOnClickListener
            }


        }

    }
}