package com.saulblanco.cryptodata.data.model

import com.saulblanco.cryptodata.data.model.network.DrinkService
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val api: DrinkService
) {
    suspend fun getAllDrinkDataFromApi(): List<Drink> {

        return api.getCryptoDataFromApi()

    }

}