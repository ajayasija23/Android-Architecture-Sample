package com.asija.assignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asija.assignment.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): ProductDao
}