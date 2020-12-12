package com.example.kingsofkode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val cardIsPurchased = MutableLiveData<Boolean>()

    fun purchase() {
        cardIsPurchased.value = true
    }

    fun reset() {
        cardIsPurchased.value = false
    }
}