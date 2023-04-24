package com.saulblanco.drinkdataapp.domain.usecases

import com.saulblanco.drinkdataapp.data.DrinkRepository
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import javax.inject.Inject

class GetDrinkListByCategory  @Inject constructor(
    private val repository: DrinkRepository
) {
    suspend operator fun invoke(category:String): List<DrinkGeneralDomain> {
        val resul = repository.getDrinkListByCategory(category)
        return resul
    }

}