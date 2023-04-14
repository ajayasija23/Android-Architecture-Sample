package com.asija.assignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.asija.assignment.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    suspend fun getAll(): List<Product>?
    @Insert
    suspend fun insertAll(posts:List<Product>)
}