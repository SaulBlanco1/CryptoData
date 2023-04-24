package com.saulblanco.drinkdataapp.domain.usecases

import com.saulblanco.drinkdataapp.data.DrinkRepository
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import javax.inject.Inject

class GetFavDrinkList @Inject constructor(
    private val repository: DrinkRepository
){

    suspend operator fun invoke():List<DrinkGeneralDomain> {

        val resul = repository.getFavDrinkListFromDB()
        return resul

    }
}