package com.ulyanaab.collectionsapp2.controllers

import com.ulyanaab.collectionsapp2.models.CollectionSeries
import com.ulyanaab.collectionsapp2.utilities.id_super_cars_1
import com.ulyanaab.collectionsapp2.utilities.id_super_cars_2

class SuperCarsCollectionController : CollectionController() {

    private val series_1_string = "super_cars_series_1"
    private val series_2_string = "super_cars_series_2"
    private val series_1_part_of_url = "https://www.laststicker.ru/i/cards/446/"
    private val series_2_part_of_url = "https://www.laststicker.ru/i/cards/983/"
    private val series_1_start_number = 0
    private val series_2_start_number = 0
    private val series_1_quantity = 260
    private val series_2_quantity = 145
    private val series_1_main_image = "https://www.laststicker.ru/i/album/446.jpg"
    private val series_2_main_image = "https://www.laststicker.ru/i/album/983.jpg"


    override fun initFields() {
        when (id) {
            id_super_cars_1 -> {
                quantity = series_1_quantity
                partOfUrl = series_1_part_of_url
                startNumber = series_1_start_number
                prefString = series_1_string
            }
            id_super_cars_2 -> {
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
                "Серия 1",
                id_super_cars_1
            ),
            CollectionSeries(
                series_2_main_image,
                "Серия 2",
                id_super_cars_2
            )
        )
    }

    override fun getTitle(): String = "Супергонки"

}