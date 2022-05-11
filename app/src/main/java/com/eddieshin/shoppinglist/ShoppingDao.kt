package com.eddieshin.shoppinglist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ShoppingDao {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun upsert(item: ShoppingItem)

    fun delete(item: ShoppingItem)
}