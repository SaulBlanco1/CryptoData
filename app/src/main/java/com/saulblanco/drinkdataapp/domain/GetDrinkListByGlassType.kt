package com.saulblanco.drinkdataapp.domain

import com.saulblanco.drinkdataapp.data.model.DrinkRepository
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import javax.inject.Inject

class GetDrinkListByGlassType @Inject constructor(
    private val repository: DrinkRepository
) {
    suspend operator fun invoke(glassType: String): List<DrinkGeneralDomain> {
        val resul = repository.getDrinkListByGlassType(glassType)
        return resul
    }
}
