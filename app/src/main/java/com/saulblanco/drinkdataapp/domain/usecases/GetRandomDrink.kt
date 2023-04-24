package com.saulblanco.drinkdataapp.domain.usecases

import com.saulblanco.drinkdataapp.data.DrinkRepository
import javax.inject.Inject

class GetRandomDrink  @Inject constructor(
    private val repository: DrinkRepository
){

    suspend operator fun invoke():String {
        val resul = repository.getRandomDrink()
        return resul

    }
}