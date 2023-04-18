package com.saulblanco.cryptodata.data.model.network

import android.util.Log
import com.saulblanco.cryptodata.data.model.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DrinkService @Inject constructor(private val api: DrinkApiClient) {

    suspend fun getCryptoDataFromApi(): List<Drink> {
        return withContext(Dispatchers.IO) {
            val response = api.getMarketDataList()
            Log.i("SAULRESLU", response.body().toString())
            response.body()?.drinkList?: emptyList()
        }

    }


}
