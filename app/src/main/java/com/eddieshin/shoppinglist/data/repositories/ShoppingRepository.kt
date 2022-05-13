package com.eddieshin.shoppinglist.data.repositories

import com.eddieshin.shoppinglist.data.db.ShoppingDatabase
import com.eddieshin.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.shoppingDao.upsert(item)

    suspend fun delete(item: ShoppingItem) = db.shoppingDao.delete(item)

    fun getAllShoppingItems() = db.shoppingDao.getAllShoppingItems()
}