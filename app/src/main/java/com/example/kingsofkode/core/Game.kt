package com.example.kingsofkode.core

class Game {
    private var characters = mutableListOf<Character>()
    private var dice = mutableListOf<Die>()
    private var cards = mutableListOf<Card>()
    private var king:Character? = null
    private var currentPlayer:Character? = null
    private var showedCards = mutableListOf<Card>()

    fun run() {

    }

    fun hasWin():Boolean {
        var count = 0
        for (character in characters) {
            if (character.isAlive()) {
                count++
            }
        }
        return count == 1
    }

    fun getCharacters():MutableList<Character> {
        return characters
    }

    fun getKing():Character {
        return king as Character
    }

    fun setKing(newKing: Character) {
        king = newKing
    }

    fun getDice(): MutableList<Die> {
        return dice
    }

    fun roll() {

    }

    fun buyCard(character:Character, card:Card):Boolean {
        return false
    }

    fun getState() {

    }

    fun getCurrentPlayer():Character {
        return currentPlayer as Character
    }

    fun getShowedCards():MutableList<Card> {
        return cards
    }
}