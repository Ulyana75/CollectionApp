package com.ulyanaab.collectionsapp2.controllers

import android.content.Context
import com.ulyanaab.collectionsapp2.models.Card
import com.ulyanaab.collectionsapp2.models.CollectionSeries
import com.ulyanaab.collectionsapp2.utilities.*

class NinjaTurtlesController : CollectionController() {

    private val series_1_string = "ninja_turtles_series_1"
    private val series_2_string = "ninja_turtles_series_2"
    private val series_3_string = "ninja_turtles_series_3"
    private val series_1_part_of_url = "https://www.laststicker.ru/i/cards/123/"
    private val series_2_part_of_url = "https://www.laststicker.ru/i/cards/274/"
    private val series_3_part_of_url = "https://www.laststicker.ru/i/cards/838/"
    private val series_1_main_image = "https://www.laststicker.ru/i/album/123.jpg"
    private val series_2_main_image = "https://www.laststicker.ru/i/album/274.jpg"
    private val series_3_main_image = "https://www.laststicker.ru/i/album/838.jpg"
    private val series_1_start_number = 0
    private val series_2_start_number = 260
    private val series_3_start_number = 520
    private val series_1_quantity = 267
    private val series_2_quantity = 260
    private val series_3_quantity = 150


    override fun initFields() {
        when (id) {
            id_turtles_ninja_1 -> {
                quantity = series_1_quantity
                partOfUrl = series_1_part_of_url
                startNumber = series_1_start_number
                prefString = series_1_string
            }
            id_turtles_ninja_2 -> {
                quantity = series_2_quantity
                partOfUrl = series_2_part_of_url
                startNumber = series_2_start_number
                prefString = series_2_string
            }
            id_turtles_ninja_3 -> {
                quantity = series_3_quantity
                partOfUrl = series_3_part_of_url
                startNumber = series_3_start_number
                prefString = series_3_string
            }
            else -> throw IllegalStateException("Wrong id")
        }
    }

    override fun getCollectionsList(): List<CollectionSeries> {
        return listOf(
            CollectionSeries(
                series_1_main_image,
                "Боевая четверка",
                id_turtles_ninja_1
            ),
            CollectionSeries(
                series_2_main_image,
                "Воины тени",
                id_turtles_ninja_2
            ),
            CollectionSeries(
                series_3_main_image,
                "Братья по оружию",
                id_turtles_ninja_3
            )
        )
    }

    override fun getTitle(): String = "Черепашки-ниндзя"

    override fun initCollection() {
        if(id == id_turtles_ninja_1) {

            val sPref = APP_ACTIVITY.getPreferences(Context.MODE_PRIVATE)
            val s = sPref.getString(prefString, "")

            for(i in 0 until quantity - 7) {
                val url = partOfUrl + (i + 1 + startNumber).toString() + ".jpg"
                cardList.add(Card((i + 1 + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[i] == '1')
                        cardList[i].isChecked = true
                }
            }

            for(i in (quantity - 7) until quantity) {
                val url = partOfUrl + 'c' + (i - quantity + 8) + ".jpg"
                cardList.add(Card('C' + (i - quantity + 8).toString(),
                    imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[i] == '1')
                        cardList[i].isChecked = true
                }
            }

        }
        else
            super.initCollection()
    }
}