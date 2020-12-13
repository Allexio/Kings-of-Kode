package com.example.kingsofkode.models

import com.example.kingsofkode.core.Character

class Card (
    val name:String,
    val price: Int,
    val activate: () -> Unit
)