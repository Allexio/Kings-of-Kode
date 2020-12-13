package com.example.kingsofkode.models

class Card (
    val name:String,
    val price: Int,
    val activate: () -> Unit
)