package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<out List<Shoe>> = _shoeList

    fun addShoe(shoe:Shoe) {
        _shoeList.value?.add(shoe)
        _shoeList.value = _shoeList.value
    }

    override fun onCleared() {
        super.onCleared()
    }
}