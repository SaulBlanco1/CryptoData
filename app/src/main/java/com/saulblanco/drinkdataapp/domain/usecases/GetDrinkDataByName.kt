package com.saulblanco.drinkdataapp.domain.usecases

import com.saulblanco.drinkdataapp.data.DrinkRepository
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import javax.inject.Inject

class GetDrinkDataByName @Inject constructor(
    private val repository: DrinkRepository
){

    suspend operator fun invoke(name:String):List<DrinkGeneralDomain> {

        val resul = repository.getDrinkDataByName(name)
        return resul

    }
}