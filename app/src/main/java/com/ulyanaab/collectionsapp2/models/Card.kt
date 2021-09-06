package com.ulyanaab.collectionsapp2.models

import java.io.Serializable

data class Card(
    val number: String = "0",
    var isChecked: Boolean = false,
    var isCheckedTemp: Boolean? = null,
    val imageUrl: String = "",
    val collectionID: Int = 0,
) : Serializable
