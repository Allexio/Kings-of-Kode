<!-- PROJECT LOGO -->

# About King of Kode

![our app in action][game-screenshot]

## Rules

### Mobile Game Adaptation

If you already know the base game rules, you may be wondering how you do the things you can do in the classic board game in this version.

* To select which dice to rethrow, simply tap the dice you want to re-throw, and the submit button will dynamically change to a "re roll" button.
(Don't forget you can only do this twice!)
* To view information about a player (you or an enemy) simply tap their image. Your information will always be displayed in the middle lower section of the screen.
* To buy a card, tap one of the three cards in the middle of the screen, then select "purchase". The button will not appear if you do not have enough energy to buy it.
* To use a card, simply tap it from your hand cards section (bottom of the screen). There will be no prompt so be careful!

### Basic Rules

This game is played against 3 AI enemies, and the goal is to win either by killing all of them, or by gaining 20 points.
This is done by rolling 6 dice every turn.
These dice are tap-able, which allows you to select some to re-throw up to two times per turn.
Once you have thrown (and re-thrown) your dice, the enemies do the same.
There are 6 die faces. 3 that let you score points, one that lets you attack enemies, one that heals you (when you are not the Kode King) and one that gives you research points to unlock power cards with.
Every other rule can be gleaned from the official King of Tokyo manual [here](https://www.iello.com/sites/default/files/2016-10/KingOfTokyo_2016_EN_Rules.pdf).

## Built With

* [Kotlin](https://kotlinlang.org/)
* [Material](https://material.io/)

## Usage

Our game is fully functional.
All you need to do is download the files from Git and build the APK.
One aspect we would have liked to work on more is the AI as we currently have a pretty barebones one.

## Contributors

* [Kevin Voyer](https://github.com/kecsou) - Back (Game logic, core mechanics)
* [Daniel Sebton](https://github.com/Allexio) - Front (UI, fragments, design, art)
* [Doreen Daunique](https://github.com/DoreenDaunique)
* [Tristan Dietz](https://github.com/Pyrrha)


[game-screenshot]: app/src/main/res/drawable/game.png
