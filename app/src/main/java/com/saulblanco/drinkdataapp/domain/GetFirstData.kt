package com.saulblanco.drinkdataapp.domain

import com.saulblanco.drinkdataapp.data.model.DrinkRepository
import com.saulblanco.drinkdataapp.data.model.Drink
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import javax.inject.Inject

class GetFirstData @Inject constructor(
    private val repository: DrinkRepository
){

    suspend operator fun invoke():List<DrinkGeneralDomain> {
         val resul = repository.getAllDrinkDataFromApi()
        return resul

    }
}