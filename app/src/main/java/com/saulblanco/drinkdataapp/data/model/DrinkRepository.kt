package com.saulblanco.drinkdataapp.data.model

import android.util.Log
import com.saulblanco.drinkdataapp.data.model.network.DrinkService
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val api: DrinkService
) {
    suspend fun getAllDrinkDataFromApi(): List<Drink> {

        return api.getCryptoDataFromApi()

    }

    suspend fun getDrinkDataByName(name:String) : List<Drink>{

        return api.getCryptoDataFromApiByName(name)
    }

    suspend fun getItemDetail(id:String): List<DrinkDetail>{
        return api.getItemDetailFromId(id)
    }

}