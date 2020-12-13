package com.example.kingsofkode.models

import com.example.kingsofkode.core.Game

class DataSource {
    companion object {
        fun getCards(game: Game):ArrayList<Card> {
            val player = game.player
            val npcPlayers = game.characters.filter { it != player }

            return arrayListOf(
                Card("fake_card", 3) {
                    // Example this card hit all npc players of one
                    for (npcPlayer in npcPlayers) {
                        if (npcPlayer.isAlive()) {
                            npcPlayer.decreaseHealth(1)
                        }
                    }
                },
                Card("fake_card", 2) {
                    // Increase player heath
                    val increaseValue = 3
                    if (player.health + increaseValue > 10) {
                        player.health = 10
                    }  else {
                        player.health += increaseValue
                    }
                },
                Card("fake_card", 1) {
                    // Increase player heath
                    val increaseValue = 1
                    if (player.health + increaseValue > 10) {
                        player.health = 10
                    }  else {
                        player.health += increaseValue
                    }
                }
            )
        }
    }
}