package com.example.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.model.ProductCache

@Database(version = 1, entities = [ProductCache::class])
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun productDao(): ProductsDao

    companion object {
        fun createDatabase(context: Context): ProductsDao {
            return Room
                .databaseBuilder(context, ProductsDatabase::class.java, "Products.db")
                .build()
                .productDao()
        }
    }

}