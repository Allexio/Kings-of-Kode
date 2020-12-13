package com.example.kingsofkode.core

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
                Card("card_health_3", 3) {
                    // Increase player heath by 3
                    player.increaseHealth(3)
                },
                Card("card_health_5", 7) {
                    // Increase player heath by 5
                    player.increaseHealth(5)
                },
                Card("card_health_55", 8) {
                    // Increase player heath (without 10 max check)
                    player.health += 5
                }
            )
        }
    }
}