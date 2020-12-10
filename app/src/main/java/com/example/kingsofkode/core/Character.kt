package com.example.kingsofkode.core

import com.example.kingsofkode.R

data class Character(val name: String) {
    var health = 10
    var energy = 0
    var score = 0
    var cards = mutableListOf<Card>()
    val imageRessourceId : Int

    init {
        when (name) {
            "go" -> this.imageRessourceId = R.drawable.go
            "java" -> this.imageRessourceId = R.drawable.java
            "php" -> this.imageRessourceId = R.drawable.php
            "python" -> this.imageRessourceId = R.drawable.python
            "rust" -> this.imageRessourceId = R.drawable.rust
            else -> this.imageRessourceId = 0
        }
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun beginTurn() {
        for (card in cards) {
            if (card.beginTurn()) {
                break
            }
        }
    }

    fun endTurn() {
        for (card in cards) {
            if (card.endTurn()) {
                break
            }
        }
    }

    fun isAlive():Boolean {
        return health > 0
    }

    fun isNearToDeath():Boolean {
        return health < 3
    }

    fun decreaseEnergy(amount: Int) {
        this.energy -= amount
    }

    fun increaseEnergy(amount: Int) {
        this.energy += amount
    }

    fun decreaseHealth(amount: Int) {
        this.health -= amount
    }

    fun increaseHealth(amount: Int) {
        this.health += amount
    }

    fun increaseScore(amount: Int) {
        this.score += amount
    }

    override fun equals(other: Any?): Boolean {
        if (other?.javaClass != this.javaClass) return false

        other as Character

        return this.name == other.name
    }
}