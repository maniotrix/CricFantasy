package com.example.cricfantasy

import androidx.fragment.app.Fragment
import com.example.cricfantasy.ui.BaseFragmentStringViewModel

open class BaseFragment : Fragment() {
    lateinit var fragmentViewModel: BaseFragmentStringViewModel
    fun getViewModel() : BaseFragmentStringViewModel {
        return this.fragmentViewModel
    }
}
