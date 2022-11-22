package com.example.cricfantasy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class MyViewModel: ViewModel() {
    private val _items = MutableLiveData<MutableList<ItemViewState>>()
    val items:LiveData<MutableList<ItemViewState>>
        get() = _items

    init {
        _items.value = ArrayList()
    }

    fun addItem(new : ItemViewState){
        _items.value?.add(new)
        _items.value = _items.value
    }
}
