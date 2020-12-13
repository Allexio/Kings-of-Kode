package com.example.kingsofkodeimport android.content.Intentimport android.media.MediaPlayerimport android.os.Bundleimport android.widget.Buttonimport androidx.appcompat.app.AppCompatActivityimport androidx.viewpager2.widget.ViewPager2import com.example.kingsofkode.BoardGame.BoardGameActivityimport java.util.*class MainActivity : AppCompatActivity() {    private val arrayList = ArrayList<String>()    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        setContentView(R.layout.character_picker_layout)        val myViewPager2 = findViewById<ViewPager2>(R.id.character_picker)        arrayList.add("go")        arrayList.add("java")        arrayList.add("php")        arrayList.add("python")        arrayList.add("rust")        val myAdapter = MyAdapter(this, arrayList)        myViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL        myViewPager2.adapter = myAdapter        val startGameButton = findViewById<Button>(R.id.start_game_button)        startGameButton.setOnClickListener {            // Récupération de l'id de la carte dans le tableau            //println(myViewPager2.currentItem%5)            //Récuperation du nom de la carte            //println(arrayList[myViewPager2.currentItem%5])            val mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.game_start_fanfare)            mediaPlayer?.start()            val boardGameIntent = Intent(this@MainActivity, BoardGameActivity::class.java)            boardGameIntent.putExtra("player", arrayList[myViewPager2.currentItem % 5])            startActivity(boardGameIntent)            //setContentView(R.layout.board_layout)        }    }}