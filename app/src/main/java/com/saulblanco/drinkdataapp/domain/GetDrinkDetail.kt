package com.saulblanco.drinkdataapp.domain

import android.util.Log
import com.saulblanco.drinkdataapp.data.model.DrinkDetail
import com.saulblanco.drinkdataapp.data.model.DrinkRepository
import javax.inject.Inject


class GetDrinkDetail @Inject constructor(
    private val repository: DrinkRepository
) {

    suspend operator fun invoke(id:String):DrinkDetail{
        val resul = repository.getItemDetail(id)
        return resul[0]
    }
}