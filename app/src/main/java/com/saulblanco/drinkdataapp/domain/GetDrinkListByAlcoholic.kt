package com.saulblanco.drinkdataapp.domain

import com.saulblanco.drinkdataapp.data.model.DrinkRepository
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import javax.inject.Inject

class GetDrinkListByAlcoholic @Inject constructor(
    private val repository: DrinkRepository
) {
    suspend operator fun invoke(alcoholic: String): List<DrinkGeneralDomain> {
        val resul = repository.getDrinkListByAlcoholic(alcoholic)
        return resul
    }
}
