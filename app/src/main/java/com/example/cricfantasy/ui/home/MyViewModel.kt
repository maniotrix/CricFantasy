package com.example.cricfantasy.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class MyViewModel: ViewModel() {
    private val _items = MutableLiveData<MutableList<ItemViewState>>()


    init {
        _items.value = ArrayList()
    }

    fun getItems(): MutableLiveData<MutableList<ItemViewState>> {
        return _items
    }

    fun addItem(new : ItemViewState){
        _items.value?.add(new)
        _items.value = _items.value
    }
}
