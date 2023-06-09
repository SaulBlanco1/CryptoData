package com.saulblanco.drinkdataapp.domain.usecases

import com.saulblanco.drinkdataapp.data.DrinkRepository
import com.saulblanco.drinkdataapp.domain.model.DrinkDetailDomain
import javax.inject.Inject


class GetDrinkDetail @Inject constructor(
    private val repository: DrinkRepository
) {

    suspend operator fun invoke(id:String): DrinkDetailDomain {
        val resul = repository.getItemDetail(id)
        return resul[0]
    }
}