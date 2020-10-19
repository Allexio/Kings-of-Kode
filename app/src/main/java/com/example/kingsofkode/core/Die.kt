package com.example.kingsofkode.core

class Die {
    private var value:Int = (1..6).random()

    fun getValue():Int {
        return value
    }
}