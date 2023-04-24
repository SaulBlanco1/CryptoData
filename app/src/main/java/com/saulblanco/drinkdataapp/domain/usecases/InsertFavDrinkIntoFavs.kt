package com.saulblanco.drinkdataapp.domain.usecases

import com.saulblanco.drinkdataapp.data.DrinkRepository
import com.saulblanco.drinkdataapp.data.database.entities.toDatabase
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import javax.inject.Inject

class InsertFavDrinkIntoFavs @Inject constructor(
    private val repository: DrinkRepository
){

    suspend operator fun invoke(favDrink:DrinkGeneralDomain) {
        val favtoInsert= favDrink.toDatabase()
        repository.insertFavDrink(favtoInsert)
    }
}