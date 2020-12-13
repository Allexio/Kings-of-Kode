package com.example.kingsofkode.core

class DataSource {
    companion object {
        fun getCards(game: Game):ArrayList<Card> {
            val player = game.player
            val npcPlayers = game.characters.filter { it != player }
            val highest_scoring_player = game.player // TODO: set this to be the actual top scoring player

            return arrayListOf(
                Card("card_damage_2", 7) {
                    // Example this card hit all npc players of one
                    for (npcPlayer in npcPlayers) {
                        if (npcPlayer.isAlive()) {
                            npcPlayer.decreaseHealth(2)
                        }
                    }
                },
                Card("card_damage_3_all", 7) {
                    // Example this card hit all npc players of one
                    for (player in game.characters) {
                        if (player.isAlive()) {
                            player.decreaseHealth(3)
                        }
                    }
                },
                Card("card_damage_3_king", 8) {
                    // Example this card hit all npc players of one
                    game.king.decreaseHealth(3)
                },
                Card("card_score_vampire_2", 6) {
                    // Example this card hit all npc players of one

                    player.score += 2
                    highest_scoring_player.score -= 2
                },
                Card("card_score_3", 6) {
                    // Example this card hit all npc players of one
                    player.score += 3
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