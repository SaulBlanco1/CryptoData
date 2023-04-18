package com.saulblanco.drinkdataapp.data.model.network

import android.util.Log
import com.saulblanco.drinkdataapp.data.model.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DrinkService @Inject constructor(private val api: DrinkApiClient) {

    suspend fun getCryptoDataFromApi(): List<Drink> {
        return withContext(Dispatchers.IO) {
            val response = api.getMarketDataListBegin()

            response.body()?.drinkList?: emptyList()
        }

    }

    suspend fun getCryptoDataFromApiByName(name:String): List<Drink> {
        return withContext(Dispatchers.IO) {

            val response = api.getDrinkByName(name)
            Log.i("SAUl",name)
            Log.i("SAUl",response.toString())
            response.body()?.drinkList?: emptyList()
        }

    }



}
