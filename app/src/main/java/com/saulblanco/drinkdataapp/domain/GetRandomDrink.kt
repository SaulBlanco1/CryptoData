package com.saulblanco.drinkdataapp.domain

import com.saulblanco.drinkdataapp.data.model.DrinkRepository
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import javax.inject.Inject

class GetRandomDrink  @Inject constructor(
    private val repository: DrinkRepository
){

    suspend operator fun invoke():String {
        val resul = repository.getRandomDrink()
        return resul

    }
}