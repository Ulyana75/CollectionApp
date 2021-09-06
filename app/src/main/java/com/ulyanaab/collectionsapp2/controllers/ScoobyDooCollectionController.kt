package com.ulyanaab.collectionsapp2.controllers

import android.content.Context
import com.ulyanaab.collectionsapp2.models.Card
import com.ulyanaab.collectionsapp2.models.CollectionSeries
import com.ulyanaab.collectionsapp2.utilities.*

class ScoobyDooCollectionController : CollectionController() {

    private val series_1_main_image = "https://www.laststicker.ru/i/album/82.jpg"
    private val series_1_part_of_url = "https://www.laststicker.ru/i/cards/82/"
    private val series_1_string = "scooby_doo_series_1"
    private val series_1_start_number = 0
    private val series_2_main_image = "https://www.laststicker.ru/i/album/905.jpg"
    private val series_2_part_of_url = "https://www.laststicker.ru/i/cards/905/"
    private val series_2_string = "scooby_doo_series_2"
    private val series_2_start_number = 0
    private val series_1_quantity = 341
    private val series_2_quantity = 350


    override fun initFields() {
        when (id) {
            id_scooby_doo_1 -> {
                quantity = series_1_quantity
                partOfUrl = series_1_part_of_url
                startNumber = series_1_start_number
                prefString = series_1_string
            }
            id_scooby_doo_2 -> {
                quantity = series_2_quantity
                partOfUrl = series_2_part_of_url
                startNumber = series_2_start_number
                prefString = series_2_string
            }
            else -> throw IllegalStateException("Wrong id")
        }
    }

    override fun getCollectionsList(): List<CollectionSeries> {
        return listOf(
            CollectionSeries(
                series_1_main_image,
                "2006 год",
                id_scooby_doo_1
            ),
            CollectionSeries(
                series_2_main_image,
                "2012 год",
                id_scooby_doo_2
            )
        )
    }

    override fun getTitle(): String = "Скуби-Ду"

    override fun initCollection() {
        var k = 0

        if(id == id_scooby_doo_1) {
            val sPref = APP_ACTIVITY.getPreferences(Context.MODE_PRIVATE)
            val s = sPref.getString(prefString, "")

            for(i in 0 until 48) {
                val url = partOfUrl + "af" + (i + 1 + startNumber).toString() + ".jpg"
                cardList.add(Card("Af" + (i + 1 + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[k] == '1')
                        cardList[k].isChecked = true
                }
                k++
            }

            for(i in 0 until 52) {
                val url = partOfUrl + "am" + (i + 1 + startNumber).toString() + ".jpg"
                cardList.add(Card("Am" + (i + 1 + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[k] == '1')
                        cardList[k].isChecked = true
                }
                k++
            }

            for(i in 0 until 60) {
                val url = partOfUrl + "as" + (i + 1 + startNumber).toString() + ".jpg"
                cardList.add(Card("As" + (i + 1 + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[k] == '1')
                        cardList[k].isChecked = true
                }
                k++
            }

            for(i in 0 until 16) {
                val url = partOfUrl + "b" + (i + 1 + startNumber).toString() + ".jpg"
                cardList.add(Card("B" + (i + 1 + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[k] == '1')
                        cardList[k].isChecked = true
                }
                k++
            }

            for(i in 0 until 77) {
                val url = partOfUrl + "eu" + (i + 1 + startNumber).toString() + ".jpg"
                cardList.add(Card("Eu" + (i + 1 + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[k] == '1')
                        cardList[k].isChecked = true
                }
                k++
            }

            for(i in 0 until 3) {
                val url: String
                if(i != 0)
                    url = partOfUrl + "f" + (i + startNumber).toString() + ".jpg"
                else
                    url = partOfUrl + "f.jpg"
                cardList.add(Card("F" + (i + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[k] == '1')
                        cardList[k].isChecked = true
                }
                k++
            }

            cardList.add(Card("GD", imageUrl=partOfUrl + "gd.jpg", collectionID=id))
            if (s != null) {
                if(s.isNotEmpty() && s[k] == '1')
                    cardList[k].isChecked = true
            }
            k++
            cardList.add(Card("GF", imageUrl=partOfUrl + "gf.jpg", collectionID=id))
            if (s != null) {
                if(s.isNotEmpty() && s[k] == '1')
                    cardList[k].isChecked = true
            }
            k++
            cardList.add(Card("GSc", imageUrl=partOfUrl + "gsc.jpg", collectionID=id))
            if (s != null) {
                if(s.isNotEmpty() && s[k] == '1')
                    cardList[k].isChecked = true
            }
            k++
            cardList.add(Card("GSh", imageUrl=partOfUrl + "gsh.jpg", collectionID=id))
            if (s != null) {
                if(s.isNotEmpty() && s[k] == '1')
                    cardList[k].isChecked = true
            }
            k++
            cardList.add(Card("GV", imageUrl=partOfUrl + "gv.jpg", collectionID=id))
            if (s != null) {
                if(s.isNotEmpty() && s[k] == '1')
                    cardList[k].isChecked = true
            }
            k++

            for(i in 0 until 16) {
                val url = partOfUrl + "h" + (i + 1 + startNumber).toString() + ".jpg"
                cardList.add(Card("H" + (i + 1 + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[k] == '1')
                        cardList[k].isChecked = true
                }
                k++
            }

            for(i in 0 until 12) {
                val url = partOfUrl + "m" + (i + 1 + startNumber).toString() + ".jpg"
                cardList.add(Card("M" + (i + 1 + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[k] == '1')
                        cardList[k].isChecked = true
                }
                k++
            }

            for(i in 0 until 36) {
                val url = partOfUrl + "oc" + (i + 1 + startNumber).toString() + ".jpg"
                cardList.add(Card("Oc" + (i + 1 + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[k] == '1')
                        cardList[k].isChecked = true
                }
                k++
            }

            for(i in 0 until 16) {
                val url = partOfUrl + "w" + (i + 1 + startNumber).toString() + ".jpg"
                cardList.add(Card("W" + (i + 1 + startNumber).toString(), imageUrl=url, collectionID=id))
                if (s != null) {
                    if(s.isNotEmpty() && s[k] == '1')
                        cardList[k].isChecked = true
                }
                k++
            }

        }
        else
            super.initCollection()
    }
}