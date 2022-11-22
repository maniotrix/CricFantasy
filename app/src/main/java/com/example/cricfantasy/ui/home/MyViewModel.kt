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
    private val _items = MutableLiveData<List<ItemViewState>>()
    val items:LiveData<List<ItemViewState>>
        get() = _items

    fun fetchItems(items : List<ItemViewState>){
        viewModelScope.launch { _items.value = items }
    }
}
