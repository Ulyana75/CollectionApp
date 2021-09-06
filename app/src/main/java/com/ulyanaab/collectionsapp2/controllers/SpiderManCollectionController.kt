package com.ulyanaab.collectionsapp2.controllers

import com.ulyanaab.collectionsapp2.models.CollectionSeries
import com.ulyanaab.collectionsapp2.utilities.id_spider_man_1
import com.ulyanaab.collectionsapp2.utilities.id_spider_man_2
import com.ulyanaab.collectionsapp2.utilities.id_spider_man_3

class SpiderManCollectionController : CollectionController() {

    private val series_1_string = "spider_man_series_1"
    private val series_2_string = "spider_man_series_2"
    private val series_3_string = "spider_man_series_3"
    private val series_1_part_of_url = "https://www.laststicker.ru/i/cards/38/"
    private val series_2_part_of_url = "https://www.laststicker.ru/i/cards/106/"
    private val series_3_part_of_url = "https://www.laststicker.ru/i/cards/166/"
    private val series_1_main_image = "https://www.laststicker.ru/i/album/38.jpg"
    private val series_2_main_image = "https://www.laststicker.ru/i/album/106.jpg"
    private val series_3_main_image = "https://www.laststicker.ru/i/album/166.jpg"
    private val series_1_start_number = 0
    private val series_2_start_number = 275
    private val series_3_start_number = 550
    private val series_1_quantity = 275
    private val series_2_quantity = 275
    private val series_3_quantity = 275


    override fun initFields() {
        when (id) {
            id_spider_man_1 -> {
                quantity = series_1_quantity
                partOfUrl = series_1_part_of_url
                startNumber = series_1_start_number
                prefString = series_1_string
            }
            id_spider_man_2 -> {
                quantity = series_2_quantity
                partOfUrl = series_2_part_of_url
                startNumber = series_2_start_number
                prefString = series_2_string
            }
            id_spider_man_3 -> {
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
                "Часть 1",
                id_spider_man_1
            ),
            CollectionSeries(
                series_2_main_image,
                "Часть 2",
                id_spider_man_2
            ),
            CollectionSeries(
                series_3_main_image,
                "Часть 3",
                id_spider_man_3
            )
        )
    }

    override fun getTitle(): String = "Человек-паук"

}