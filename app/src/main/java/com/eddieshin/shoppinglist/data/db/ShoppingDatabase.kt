package com.eddieshin.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eddieshin.shoppinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)


abstract class ShoppingDatabase : RoomDatabase() {

    abstract val shoppingDao: ShoppingDao

    companion object {
        @Volatile // one thread at a time
        private var instance: ShoppingDatabase? = null

        private val LOCK = Any()

        // Everytime we create an instance ShoppingDatabase, it returns ShoppingDatabase instance.
        // If an instance already exists,
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java, "ShoppingDB.db").build()
    }
}

/*
abstract class ShoppingDatabase : RoomDatabase() {

    abstract val shoppingDao: ShoppingDao

    companion object {
        @Volatile // one thread at a time
        private var INSTANCE: ShoppingDatabase? = null

        private val LOCK = Any()

        fun getInstance(context: Context): ShoppingDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingDatabase::class.java,
                    "ShoppingDB.db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
 */