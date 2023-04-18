package com.saulblanco.cryptodata.domain

import com.saulblanco.cryptodata.data.model.DrinkRepository
import com.saulblanco.cryptodata.data.model.Drink
import javax.inject.Inject

class GetFirstData @Inject constructor(
    private val repository: DrinkRepository
){

    suspend operator fun invoke():List<Drink> {
         val resul = repository.getAllDrinkDataFromApi()

        return resul

    }
}