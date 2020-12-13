package com.example.kingsofkode.boardgameimport android.content.DialogInterfaceimport android.content.Intentimport android.media.MediaPlayerimport android.os.Bundleimport android.view.View.GONEimport android.widget.*import androidx.appcompat.app.AlertDialogimport androidx.appcompat.app.AppCompatActivityimport androidx.core.content.ContextCompatimport androidx.recyclerview.widget.LinearLayoutManagerimport com.example.kingsofkode.gameover.GameOverActivityimport com.example.kingsofkode.Rimport com.example.kingsofkode.core.Gameimport com.example.kingsofkode.core.Cardimport com.example.kingsofkode.Utils.Companion.getDrawableFromStringimport com.example.kingsofkode.core.Characterimport kotlinx.android.synthetic.main.board_layout.*class BoardGameActivity : AppCompatActivity() {    private lateinit var game: Game    private lateinit var dice: ArrayList<ImageButton>    private lateinit var cardAdapter: CardInHandRecyclerAdapter    private lateinit var characterCardList: ArrayList<ImageView>    private lateinit var powerCardList: ArrayList<ImageView>    private var listOfDiceState = BooleanArray(6){false}    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        setContentView(R.layout.board_layout)        val playerName = this.intent.getStringExtra("player")!!        val rollButton = findViewById<Button>(R.id.roll_button)        this.game = Game(playerName)        this.initRecyclerView()        this.dice = arrayListOf(            findViewById(R.id.die1),            findViewById(R.id.die2),            findViewById(R.id.die3),            findViewById(R.id.die4),            findViewById(R.id.die5),            findViewById(R.id.die6)        )        this.characterCardList = arrayListOf(            findViewById(R.id.center_character),            findViewById(R.id.character_one),            findViewById(R.id.character_two),            findViewById(R.id.character_three)        )        this.powerCardList = arrayListOf(            findViewById(R.id.left_power_card),            findViewById(R.id.center_power_card),            findViewById(R.id.right_power_card)        )        for (characterCard in characterCardList) {            characterCard.setOnClickListener {                val character = game.characters.find { it.currentImageView == characterCard }                if (character != null) {                    CharacterDetailsFragment(character)                        .show(supportFragmentManager, CharacterDetailsFragment.TAG)                }            }        }        for (i in 0 until this.dice.size) {            val die = this.dice[i]            die.setOnClickListener { onDiceClick(i, die) }        }        setGameState()        for (j in 0..2) {            powerCardList[j].setImageDrawable(                getDrawableFromString(                    this,                    game.powerCardDeck[j].name,                    resources                )            )        }        rollButton.setOnClickListener{            val mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.dice_roll)            mediaPlayer?.start()            if (rollButton.text == "SUBMIT") {                // play first NPC after player's turn                game.turn()                rollButton.text = "NEXT TURN"                setGameState()            }            else if (rollButton.text == "NEXT TURN") {                resetDiceUI()                // end NPC turn                game.turn()                setGameState()                if (game.player == game.king && game.playerWasHit) {                    val onPositive: (dialog: DialogInterface, which: Int) -> Unit = { _, _ ->                        game.playerAbdicates()                        characterCardUpdate()                        Toast.makeText(this, "You abdicated", Toast.LENGTH_SHORT).show()                    }                    val onNegative: (dialog: DialogInterface, which: Int) -> Unit = { _, _ ->                        Toast.makeText(this, "You keep the king's place !", Toast.LENGTH_SHORT).show()                    }                    this.showDialogAlert(                        "Abdicate",                        "Do you want to abdicate ?",                        onPositive,                        onNegative                    )                }                // if it's player's turn now                if (game.player == game.currentPlayer) {                    rollButton.text = "ROLL"                    resetDiceUI()                }            }            else {                // do a re-roll                val diceStates = ArrayList<Int>()                for (i in listOfDiceState.indices) {                    if (listOfDiceState[i]) {                        diceStates.add(i)                    }                }                if (game.rollsRemaining == 3) {                    diceStates.addAll(listOf(0, 1, 2, 3, 4, 5))                }                game.reRoll(diceStates)                diceUpdate()                // reset dice                listOfDiceState = BooleanArray(6){false}                for (i in 0 until this.dice.size) {                    val die = this.dice[i]                    die.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackground))                }                roll_button.text = "SUBMIT"            }        }        for (i in 0..2) {            val powerCard = powerCardList[i]            powerCard.setOnClickListener {                val powerCard = game.powerCardDeck[i]                var cardIsBuyable = game.player.energy >= powerCard.price                val fragment = CardZoomFragment.newInstance(powerCard.name, cardIsBuyable)                fragment.setOnPurchaseCallback {                    game.player.decreaseEnergy(powerCard.price)                    addCardToHand(powerCard)                    game.removeCardFromDeck(powerCard.name)                    setGameState()                    when (game.powerCardDeck.size) {                        0 -> powerCardList[0].visibility = GONE                        1 -> {                            powerCardList[1].visibility = GONE                            powerCardList[0].setImageDrawable(getDrawableFromString(this, game.powerCardDeck[0].name, resources))                        }                        2 -> {                            powerCardList[2].visibility = GONE                            powerCardList[0].setImageDrawable(getDrawableFromString(this, game.powerCardDeck[0].name, resources))                            powerCardList[1].setImageDrawable(getDrawableFromString(this, game.powerCardDeck[1].name, resources))                        }                        else -> { // Note the block                            for (j in 0..2) {                                powerCardList[j].setImageDrawable(getDrawableFromString(this, game.powerCardDeck[j].name, resources))                            }                        }                    }                }                fragment.show(supportFragmentManager, CardZoomFragment.TAG)            }        }    }    private fun initRecyclerView() {        card_recylcer_view.apply {            layoutManager = LinearLayoutManager(this@BoardGameActivity, LinearLayoutManager.HORIZONTAL, false)            cardAdapter = CardInHandRecyclerAdapter()            cardAdapter.setOnCardActivate { card ->                setGameState()                removeCardFromHand(card.name)            }            adapter = cardAdapter        }    }    private fun addCardToHand(card: Card) {        game.addCardToHand(card)        cardAdapter.addCard(card)    }    private fun removeCardFromHand(name: String) {        game.removeCardFromHand(name)        cardAdapter.removeCard(name)    }    private fun resetDiceUI() {        for (i in 0 until this.dice.size) {            val die = this.dice[i]            die.setImageDrawable(getDrawableFromString(this, "die", resources))        }    }    private fun diceUpdate() {        // updates dice in the UI with dice in the game state        for (i in 0 until this.dice.size) {            val die = this.dice[i]            die.setImageDrawable(getDrawableFromString(this, game.dice[i], resources))        }    }    private fun onDiceClick(dieNumber: Int, die: ImageButton) {        // what happens when a dice icon is clicked        if (game.rollsRemaining == 0            || roll_button.text == "ROLL"            || game.currentPlayer != game.player        ) {            return        }        listOfDiceState[dieNumber] = !listOfDiceState[dieNumber]        if (listOfDiceState[dieNumber])            die.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))        else            die.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackground))        val rollButton = findViewById<Button>(R.id.roll_button)        if (listOfDiceState.find{ it } == null) {            rollButton.text = "SUBMIT"        }        else {            rollButton.text = "RE-ROLL"        }    }    private fun setGameState() {        if (game.state != "running") {            val gameOverIntent = Intent(this@BoardGameActivity, GameOverActivity::class.java)            gameOverIntent.putExtra("gameState", game.state)            // move to game over screen            // get current top scores            val scoreArray = getScoreBoard()            gameOverIntent.putExtra("scoreArray", scoreArray)            // get current top players            val playerRankings = getPlayerRankings(scoreArray)            gameOverIntent.putExtra("playerRankings", playerRankings)            startActivity(gameOverIntent)        }        // set the dice        diceUpdate()        statusUpdate()        characterCardUpdate()        setActivePlayer()    }    private fun setActivePlayer() {        // first reset backgrounds on every image        for (character in game.characters) {            character.disableView()        }        game.currentPlayer.activeView(            getDrawableFromString(                this,                "active_player_background",                resources            )        )    }    private fun getScoreBoard() : ArrayList<Int> {        val scoreArray = arrayListOf<Int>()        for (character in game.characters) {            scoreArray.add(character.score)        }        scoreArray.sort()        scoreArray.reverse()        return scoreArray    }    private fun getPlayerRankings(scoreArray: ArrayList<Int>) : ArrayList<String> {        val playerRankings = arrayListOf<String>()        for (score in scoreArray) {            for (character in game.characters) {                if (character.score == score && !playerRankings.contains(character.name)) {                    playerRankings.add(character.name)                }            }        }        return playerRankings    }    private fun statusUpdate() {        val score = findViewById<TextView>(R.id.popularity_points)        val energy = findViewById<TextView>(R.id.research_points)        val health = findViewById<TextView>(R.id.breaking_change_points)        score.text = game.player.score.toString()        energy.text = game.player.energy.toString()        health.text = game.player.health.toString()    }    private fun characterCardUpdate() {        characterCardList[0].setImageDrawable(            getDrawableFromString(                this,                game.king.name,                resources            )        )        game.king.setImageView(characterCardList[0])        val simpleCharacterCardList = characterCardList.subList(1, characterCardList.size)        val simpleCharacterList = game.characters.filter { it != game.king }        for (i in 0 until simpleCharacterCardList.size) {            val character = simpleCharacterList[i]            val simpleCharacterCard = simpleCharacterCardList[i]            character.setImageView(simpleCharacterCard)            simpleCharacterCard.setImageDrawable(                getDrawableFromString(                    this,                    character.name,                    resources                )            )            if (!character.isAlive()) {                simpleCharacterCard.visibility = GONE            }        }    }    private fun showDialogAlert(        title: String,        message: String,        onPositive: (dialog: DialogInterface, which: Int) -> Unit,        onNegative: (dialog: DialogInterface, which: Int) -> Unit    ) {        val builder = AlertDialog.Builder(this)        builder.setTitle(title)        builder.setMessage(message)        builder.setPositiveButton("Yes", onPositive)        builder.setNegativeButton("No", onNegative)        builder.show()    }}