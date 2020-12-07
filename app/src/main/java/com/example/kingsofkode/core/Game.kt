package com.example.kingsofkode.core

class Game(playerName: String) {
    private val diceNameList = Array(6) {
        "die_1"
        "die_2"
        "die_3"
        "die_attack"
        "die_energy"
        "die_health"
    }
    var dice = diceNameList.copyOf()
    val characters = ArrayList<Character>()
    var cards = mutableListOf<Card>()

    var king:Character
    var currentPlayer:Character
    var player:Character
    var showedCards = mutableListOf<Card>()
    var reRollCount = 0
    var state = "running" // running | loss | win
    var playerTurn = true
    var rollsRemaining = 0 // 2 | 1 | 0
    var playerWasHit = false
    var playerWasHitBy:Character

    val languages = Array(5) {
        "Go"
        "Python"
        "Rust"
        "Php"
        "Java"
    }

    init {
        val availableLanguages = languages.filter { it != playerName }
        this.initDice()
        this.player = Character(playerName)
        this.currentPlayer = this.player
        this.characters.add(this.player)
        this.playerWasHitBy = this.player

        for (availableLanguage in availableLanguages) {
            this.characters.add(Character(availableLanguage))
        }
        this.king = this.characters[(0 until 4).random()]
    }

    private fun initDice() {
        for (i in 0..5) {
            this.dice[i] = "die"
        }
    }

    fun reRoll(listUpdate: ArrayList<Int>) {
        this.rollsRemaining--
        this.reRollCount++
        for (index in listUpdate) {
            val randomIndex = (0 until 6).random()
            val diceName = this.diceNameList[randomIndex]
            this.dice[index] = diceName
        }
    }

    fun turn() {
        this.reRollCount = 0
        val healthTotal = this.dice.count { it == "die_health" }
        val energyTotal = this.dice.count { it == "die_energy" }
        val attackTotal = this.dice.count { it == "die_attack" }
        val oneTotal = this.dice.count { it == "die_1" }
        val twoTotal = this.dice.count { it == "die_2" }
        val threeTotal = this.dice.count { it == "die_3" }
        this.currentPlayer.increaseScore(this.calculateScoreIncrement(oneTotal, twoTotal, threeTotal))
        this.currentPlayer.increaseHealth(healthTotal)
        this.currentPlayer.increaseEnergy(energyTotal)
        this.rollsRemaining = 2

        if (this.currentPlayer == this.king) {
            for (character in this.characters.filter { it != this.currentPlayer }) {
                character.decreaseHealth(attackTotal)
            }
        } else {
            this.king.decreaseHealth(attackTotal)
            if (this.player == this.king) {
                this.playerWasHit = true
                this.playerWasHitBy = this.currentPlayer
            } else {
                this.playerWasHit = false
            }
        }

        if (!this.currentPlayer.isAlive()) {
            this.state = "loss"
            return
        }

        if (this.currentPlayer.score >= 10) {
            this.state = if (this.currentPlayer == this.player) "win" else "loss"
            return
        }

        val indexCurrentPlayer = this.characters.indexOf(this.currentPlayer)
        if (indexCurrentPlayer == this.characters.size - 1) {
            this.currentPlayer = this.characters[0]
        } else {
            this.currentPlayer = this.characters[indexCurrentPlayer + 1]
            this.playerTurn = this.currentPlayer == player
        }
    }

    fun playerAbdicate() {
        this.king = this.playerWasHitBy!!
    }

    private fun calculateScoreIncrement(oneTotal: Int, twoTotal: Int, threeTotal: Int):Int {
        var totalIncrement = 0

        if (oneTotal > 2) {
            totalIncrement += oneTotal - 2
        }

        if (twoTotal > 2) {
            totalIncrement += oneTotal - 1
        }

        if (threeTotal > 2) {
            totalIncrement += oneTotal
        }

        return totalIncrement
    }

    fun buyCard(character:Character, card:Card):Boolean {
        return false
    }
}