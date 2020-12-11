package com.example.kingsofkode.core

import android.graphics.drawable.Drawable
import android.widget.ImageView

data class Character(val name: String) {
    var health = 10
    var energy = 0
    var score = 0
    var cards = mutableListOf<Card>()
    var currentImageView : ImageView? = null

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
        if (this.health > 10) {
            this.health = 10
        }
    }

    fun increaseScore(amount: Int) {
        this.score += amount
    }

    fun setImageView(imageView: ImageView) {
        this.currentImageView = imageView
    }

    fun activeView(drawable: Drawable?) {
        if (this.currentImageView != null) {
            this.currentImageView!!.background = drawable
        }
    }

    fun disableView() {
        if (this.currentImageView != null) {
            this.currentImageView!!.setBackgroundResource(0)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other?.javaClass != this.javaClass) return false

        other as Character

        return this.name == other.name
    }
}