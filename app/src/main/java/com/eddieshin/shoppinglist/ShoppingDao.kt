package com.eddieshin.shoppinglist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    // if the item is already in the DB, we replace the old one with the new one
    suspend fun upsert(item: ShoppingItem)
    // mix of update and insert
    // if parameter item is already in the database, update
    // otherwise, insert item

    @Delete()
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>>
    // LiveData object will make the update process on RV really efficient

}