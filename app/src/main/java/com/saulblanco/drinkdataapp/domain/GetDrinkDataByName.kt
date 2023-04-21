package com.saulblanco.drinkdataapp.domain

import android.util.Log
import com.saulblanco.drinkdataapp.data.model.Drink
import com.saulblanco.drinkdataapp.data.model.DrinkRepository
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