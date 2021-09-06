package com.ulyanaab.collectionsapp2.controllers

import android.content.Context
import com.ulyanaab.collectionsapp2.models.Card
import com.ulyanaab.collectionsapp2.models.CollectionSeries
import com.ulyanaab.collectionsapp2.utilities.APP_ACTIVITY
import com.ulyanaab.collectionsapp2.utilities.showToast
import java.io.Serializable

abstract class CollectionController : Serializable {

    protected var id: Int = 0

    protected var cardList = mutableListOf<Card>()
    protected var quantity: Int = 0
    protected var partOfUrl: String = ""
    protected var startNumber: Int = 0
    protected var prefString: String = ""


    abstract fun initFields()
    abstract fun getCollectionsList(): List<CollectionSeries>
    abstract fun getTitle(): String

    open fun initCollection() {
        val sPref = APP_ACTIVITY.getPreferences(Context.MODE_PRIVATE)
        val s = sPref.getString(prefString, "")

        for (i in 0 until quantity) {
            val url = partOfUrl + (i + 1 + startNumber).toString() + ".jpg"
            cardList.add(Card((i + 1 + startNumber).toString(), imageUrl = url, collectionID = id))
            if (s != null) {
                if (s.isNotEmpty() && s[i] == '1')
                    cardList[i].isChecked = true
            }
        }
    }

    fun setCollectionId(id: Int): CollectionController {
        this.id = id
        cardList = mutableListOf()
        initFields()
        initCollection()
        return this
    }

    fun getEditCardList(): List<Card> {
        return cardList
    }

    fun getHaveCardList(): List<Card> {
        return cardList.filter {
            it.isChecked
        }
    }

    fun getNoCardList(): List<Card> {
        return cardList.filter {
            !it.isChecked
        }
    }

    fun saveCollection() {
        val sPref = APP_ACTIVITY.getPreferences(Context.MODE_PRIVATE)
        val editor = sPref.edit()
        var s = ""

        for (i in 0 until quantity) {
            s += if (cardList[i].isCheckedTemp != null) {
                if (cardList[i].isCheckedTemp == true) {
                    cardList[i].isChecked = true
                    "1"
                } else {
                    cardList[i].isChecked = false
                    "0"
                }
            } else {
                if (cardList[i].isChecked) {
                    "1"
                } else {
                    "0"
                }
            }
        }

        editor.putString(prefString, s)
        editor.apply()

        showToast("Данные обновлены")
    }

    fun getLeftCardQuantity(): Int {
        return quantity - getHaveCardList().size
    }

}