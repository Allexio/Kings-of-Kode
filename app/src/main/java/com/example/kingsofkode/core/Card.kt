package com.example.kingsofkode.core

abstract class Card {

    open fun beginTurn(): Boolean {
        return false
    }

    open fun attack(): Boolean {
        return false
    }

    open fun receiveDamages(): Boolean {
        return false
    }

    fun endTurn(): Boolean {
        return false
    }
}