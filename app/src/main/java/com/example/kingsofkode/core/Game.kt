package com.example.kingsofkode.core

import com.example.kingsofkode.models.Card
import com.example.kingsofkode.models.DataSource

class Game(playerName: String) {
    private val diceNameList = arrayOf("die_1", "die_2", "die_3", "die_attack", "die_energy", "die_health")
    val dice = diceNameList.copyOf()

    private val languages = arrayOf("go", "python", "rust", "php", "java")
    val characters = ArrayList<Character>()
    private val diceIndexList = ArrayList<Int>()
    var powerCardDeck = ArrayList<Card>()
    var cardsInHand = ArrayList<Card>()

    var king:Character
    var currentPlayer:Character
    var player:Character

    var state = "running" // running | loss | win
    var rollsRemaining = 3 // 3 | 2 | 1 | 0
    var playerWasHit = false
    // TODO: Fix playerWasHit sometimes triggering one turn late and sometimes triggering for no reason
    var playerWasHitBy:Character
    var charactersAlive: Int = 3

    init {
        val availableLanguages = ArrayList<String>()
        availableLanguages.addAll(languages.filter { it != playerName })

        this.initDice()
        this.player = Character(playerName)
        this.currentPlayer = this.player
        this.characters.add(this.player)
        this.playerWasHitBy = this.player

        do {
            val characterName = availableLanguages.random()
            this.characters.add(Character(characterName))
            availableLanguages.remove(characterName)
        } while (this.characters.size != 4)

        this.king = this.player
        //this.king = this.characters[(0 until 5).random()]
        this.diceIndexList.addAll(0 until 6)
        this.powerCardDeck = DataSource.getCards(this)
    }

    private fun initDice() {
        for (i in 0 until 6) {
            this.dice[i] = "die"
        }
    }

    fun roll(listUpdate: ArrayList<Int>) {
        for (index in listUpdate) {
            val randomIndex = (0 until 6).random()
            val diceName = this.diceNameList[randomIndex]
            this.dice[index] = diceName
        }
    }

    fun reRoll(listUpdate: ArrayList<Int>) {
        this.rollsRemaining--
        this.roll(listUpdate)
    }

    fun turn() {
        val healthTotal = this.dice.count { it == "die_health" }
        val energyTotal = this.dice.count { it == "die_energy" }
        val attackTotal = this.dice.count { it == "die_attack" }
        val oneTotal = this.dice.count { it == "die_1" }
        val twoTotal = this.dice.count { it == "die_2" }
        val threeTotal = this.dice.count { it == "die_3" }
        val indexCurrentPlayer = this.characters.indexOf(this.currentPlayer)
        val playerHealthBeforeHits = this.player.health
        this.currentPlayer.increaseScore(this.calculateScoreIncrement(oneTotal, twoTotal, threeTotal))
        this.currentPlayer.increaseHealth(healthTotal)
        this.currentPlayer.increaseEnergy(energyTotal)
        this.rollsRemaining = 3

        if (this.currentPlayer == this.king) {
            for (character in this.characters.filter { it != this.currentPlayer }) {
                character.decreaseHealth(attackTotal)
                if (!character.isAlive()) {
                    this.charactersAlive--
                    if (this.charactersAlive == 1) {
                        this.state = if (this.currentPlayer == this.player) "win" else "loss"
                        return
                    }
                }
            }
        } else {
            this.king.decreaseHealth(attackTotal)
            if (!this.king.isAlive()) {
                this.charactersAlive--
                this.king = this.currentPlayer
            }
        }

        if (playerHealthBeforeHits > this.player.health) {
            this.playerWasHit = true
            this.playerWasHitBy = this.currentPlayer
        } else {
            this.playerWasHit = false
        }

        if (!this.player.isAlive()) {
            this.state = "loss"
            return
        }

        if (this.currentPlayer.score >= 20) {
            this.state = if (this.currentPlayer == this.player) "win" else "loss"
            return
        }

        if (indexCurrentPlayer == this.characters.size - 1) {
            this.currentPlayer = this.characters[0]
        } else {
            this.currentPlayer = this.characters[indexCurrentPlayer + 1]

            if (this.currentPlayer != this.player) {
                this.roll(this.diceIndexList)
            }
        }
    }

    fun playerAbdicates() {
        this.king = this.playerWasHitBy!!
    }

    fun removeCardFromDeck(name: String) {
        powerCardDeck = powerCardDeck.filter { it.name != name } as ArrayList<Card>
    }

    fun addCardToDeck(card: Card) {
        powerCardDeck.add(card)
    }

    fun removeCardFromHand(name: String) {
        cardsInHand = cardsInHand.filter { it.name != name } as ArrayList<Card>
    }

    fun addCardToHand(card: Card) {
        cardsInHand.add(card)
    }

    private fun calculateScoreIncrement(oneTotal: Int, twoTotal: Int, threeTotal: Int):Int {
        var totalIncrement = 0

        if (oneTotal > 2) {
            totalIncrement += oneTotal - 2
        }

        if (twoTotal > 2) {
            totalIncrement += twoTotal - 1
        }

        if (threeTotal > 2) {
            totalIncrement += threeTotal
        }

        return totalIncrement
    }

    fun buyCard(character:Character, card:Card):Boolean {
        return false
    }
}