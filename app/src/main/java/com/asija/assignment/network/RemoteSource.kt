package com.asija.assignment.network

import com.asija.assignment.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface RemoteSource {

    @GET("products")
    suspend fun getProducts():Response<List<Product>>
}