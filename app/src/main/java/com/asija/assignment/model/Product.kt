package com.asija.assignment.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "products")
@JsonClass(generateAdapter = true)
data class Product(
    @PrimaryKey
    @field:Json(name = "id")
    var id: Int,
    @ColumnInfo(name = "category")
    @field:Json(name = "category")
    var category: String,
    @ColumnInfo(name = "description")
    @field:Json(name = "description")
    var description: String,
    @ColumnInfo(name = "image")
    @field:Json(name = "image")
    var image: String,
    @ColumnInfo(name = "price")
    @field:Json(name = "price")
    var price: Double,
    @ColumnInfo(name = "title")
    @field:Json(name = "title")
    var title: String
)