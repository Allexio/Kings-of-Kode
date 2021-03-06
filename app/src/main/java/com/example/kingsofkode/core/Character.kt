package com.example.kingsofkode.core

import android.graphics.drawable.Drawable
import android.widget.ImageView

data class Character(val name: String) {
    var health = 10
    var energy = 0
    var score = 0
    var currentImageView : ImageView? = null

    fun isAlive():Boolean {
        return health > 0
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

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + health
        result = 31 * result + energy
        result = 31 * result + score
        result = 31 * result + (currentImageView?.hashCode() ?: 0)
        return result
    }
}