package com.example.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.local.model.ProductCache
import com.example.domain.entity.Product
import io.reactivex.Single

@Dao
interface ProductsDao {

    @Query("SELECT * from products")
    fun getProducts(): Single<List<ProductCache>>

    @Transaction
    fun updateData(products: List<ProductCache>) {
        deleteAll()
        insertAll(products)
    }

    @Insert
    fun insertAll(products: List<ProductCache>)

    @Query("DELETE FROM products")
    fun deleteAll()
}