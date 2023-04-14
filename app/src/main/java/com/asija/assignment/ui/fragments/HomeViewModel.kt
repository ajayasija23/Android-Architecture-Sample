package com.asija.assignment.ui.fragments

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asija.assignment.model.Product
import com.asija.assignment.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ProductsRepository):ViewModel() {

    val productsLiveData:LiveData<List<Product>>
    val error:LiveData<String>
    var isLoading=ObservableField<Boolean>(false)
    init {
        productsLiveData= repository.productsLiveData
        error=repository.error
    }
    fun getProducts(){
        viewModelScope.launch {
            isLoading.set(true)
            repository.getProducts()
            isLoading.set(false)
        }
    }
}