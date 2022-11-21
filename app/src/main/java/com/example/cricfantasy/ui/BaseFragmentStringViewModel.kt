package com.example.cricfantasy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseFragmentStringViewModel (name: String): ViewModel(){

   private val _text = MutableLiveData<String>().apply {
        value = "This is $name Fragment"
    }
   val text: LiveData<String> = _text
}
