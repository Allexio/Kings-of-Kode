package com.example.kingsofkode.core

class Character(name: String) {
    private var health = 10
    private var energy = 0
    private var name = ""
    private var cards = mutableListOf<Card>()

    init {
        this.name =  name
    }

    fun addCard(card: Card) {
        cards.add(card)
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