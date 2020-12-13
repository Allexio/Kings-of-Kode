package com.example.kingsofkode.core

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.example.kingsofkode.models.Card
import java.util.ArrayList

data class Character(val name: String) {
    var health = 10
    var energy = 0
    var score = 0
    var cards = ArrayList<Card>()
    var currentImageView : ImageView? = null

    fun addCard(card: Card) {
        cards.add(card)
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