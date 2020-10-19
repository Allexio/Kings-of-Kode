package com.example.kingsofkode.core

class Character(name: String) {
    var health = 10
    var energy = 0

    fun addCard() {

    }

    fun beginTurn() {

    }

    fun endTurn() {

    }

    fun isAlive():Boolean {
        return health != 0
    }

    fun isNearToDeath():Boolean {
        return health < 3
    }

    fun decreaseEnergy() {
        if (energy > 1) {
            energy--
        }
    }

    fun increaseEnergy() {
        energy++
    }

    fun decreaseLife() {
        if (health > 1) {
            health--
        }
    }

    fun increaseLife() {
        health++
    }
}