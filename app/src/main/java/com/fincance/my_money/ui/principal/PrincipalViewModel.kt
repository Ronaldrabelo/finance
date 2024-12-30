package com.fincance.my_money.ui.principal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrincipalViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is principal Fragment"
    }
    val text: LiveData<String> = _text
}