package com.saulblanco.drinkdataapp.domain.usecases

import com.saulblanco.drinkdataapp.data.DrinkRepository
import com.saulblanco.drinkdataapp.data.database.entities.toDatabase
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import javax.inject.Inject

class DeleteFavDrinkFromFavs @Inject constructor(
    private val repository: DrinkRepository
){

    suspend operator fun invoke(favDrink:DrinkGeneralDomain) {
        val favtoDelete= favDrink.toDatabase()
        repository.deleteFavDrink(favtoDelete)
    }
}