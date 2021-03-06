package com.eddieshin.shoppinglist.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem (
    @ColumnInfo(name="item_name")
    val name: String,
    @ColumnInfo(name="item_amount")
    var amount: Int
    ){
    @PrimaryKey(autoGenerate = true)
    // primary key of ids are generated automatically
    var id: Int? = null
}