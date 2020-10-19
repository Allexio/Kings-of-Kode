package com.example.kingsofkode.core

abstract class Card {

    open fun beginTurn(): Boolean {
        return false
    }

    fun endTurn(): Boolean {
        return false
    }
}