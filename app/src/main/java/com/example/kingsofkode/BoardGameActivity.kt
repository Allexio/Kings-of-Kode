package com.example.kingsofkodeimport android.content.Contextimport android.graphics.Colorimport android.media.MediaPlayerimport android.os.Bundleimport android.widget.Buttonimport android.widget.ImageButtonimport androidx.appcompat.app.AppCompatActivityimport androidx.core.content.ContextCompatimport com.example.kingsofkode.core.Gameimport kotlinx.android.synthetic.main.board_layout.*class BoardGameActivity : AppCompatActivity() {    private val playerName = this.intent.getStringExtra("player")    private var game : Game    private val context: Context? = null    private var listOfDiceState = BooleanArray(6){false}    init{        this.game = playerName?.let { Game(it) }!!    }    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        setContentView(R.layout.board_layout)        // Die state management        val die1 = findViewById<ImageButton>(R.id.die1)        val die2 = findViewById<ImageButton>(R.id.die2)        val die3 = findViewById<ImageButton>(R.id.die3)        val die4 = findViewById<ImageButton>(R.id.die4)        val die5 = findViewById<ImageButton>(R.id.die5)        val die6 = findViewById<ImageButton>(R.id.die6)        die1.setOnClickListener{onDiceClick(0, die1)}        die2.setOnClickListener{onDiceClick(1, die2)}        die3.setOnClickListener{onDiceClick(2, die3)}        die4.setOnClickListener{onDiceClick(3, die4)}        die5.setOnClickListener{onDiceClick(4, die5)}        die6.setOnClickListener{onDiceClick(5, die6)}        val rollButton = findViewById<Button>(R.id.roll_button)        rollButton.setOnClickListener{            val mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.dice_roll)            mediaPlayer?.start()            if (rollButton.text == "SUBMIT") {                game.turn()            }            else {                game.reRoll()                diceUpdate()            }        }    }    private fun diceUpdate() {        val die1 = findViewById<ImageButton>(R.id.die1)        val die2 = findViewById<ImageButton>(R.id.die2)        val die3 = findViewById<ImageButton>(R.id.die3)        val die4 = findViewById<ImageButton>(R.id.die4)        val die5 = findViewById<ImageButton>(R.id.die5)        val die6 = findViewById<ImageButton>(R.id.die6)        die1.setImageDrawable(getDrawableFromString(context, game.dice[0]))        die2.setImageDrawable(getDrawableFromString(context, game.dice[1]))        die3.setImageDrawable(getDrawableFromString(context, game.dice[2]))        die4.setImageDrawable(getDrawableFromString(context, game.dice[3]))        die5.setImageDrawable(getDrawableFromString(context, game.dice[4]))        die6.setImageDrawable(getDrawableFromString(context, game.dice[5]))    }    private fun onDiceClick(dieNumber: Int, die: ImageButton) {        listOfDiceState[dieNumber] = !listOfDiceState[dieNumber]        if (listOfDiceState[dieNumber])            die.setBackgroundColor(Color.DKGRAY)        else            die.setBackgroundColor(Color.LTGRAY)        val rollButton = findViewById<Button>(R.id.roll_button)        if (listOfDiceState.find{ it } == null) {            rollButton.text = "SUBMIT"        }        else {            rollButton.text = "ROLL"        }    }    private fun setGameState() {        if (game.state != "running") {            // TODO: Add function call to move to game over state            if (game.state == "win") {                // TODO: move to victory state            }            else {                // TODO: move to defeat state            }        }        // set the dice        diceUpdate()    }    private fun getDrawableFromString(c: Context, drawableName: String) {        var res = c.let { ContextCompat.getDrawable(            it, resources.getIdentifier(                drawableName,                "drawable", c.packageName            )            //TODO: return drawable        ) }    }}