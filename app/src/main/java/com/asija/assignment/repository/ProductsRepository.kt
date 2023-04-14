package com.asija.assignment.repository

import androidx.lifecycle.MutableLiveData
import com.asija.assignment.db.ProductDao
import com.asija.assignment.model.Product
import com.asija.assignment.network.RemoteSource

class ProductsRepository(private val remoteSource: RemoteSource, private val localSource:ProductDao) {

    val productsLiveData=MutableLiveData<List<Product>>()
    val error=MutableLiveData<String>()

    suspend fun getProducts(){
        try {
            val products=localSource.getAll()
            if (products.isNullOrEmpty()){
                val response=remoteSource.getProducts()
                if (response.isSuccessful){
                    localSource.insertAll(response.body()!!)
                    productsLiveData.postValue(localSource.getAll())
                }else{
                    error.postValue(response.message())
                }
            }else{
                productsLiveData.postValue(products!!)
            }
        }
        catch (e:Exception){
            error.postValue(e.localizedMessage)
        }
    }
}