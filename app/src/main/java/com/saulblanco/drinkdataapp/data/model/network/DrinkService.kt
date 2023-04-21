package com.saulblanco.drinkdataapp.data.model.network

import com.saulblanco.drinkdataapp.data.model.Drink
import com.saulblanco.drinkdataapp.data.model.DrinkDetail
import com.saulblanco.drinkdataapp.data.model.DrinkGeneral
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DrinkService @Inject constructor(private val api: DrinkApiClient) {

    suspend fun getCryptoDataFromApi(): List<Drink> {
        return withContext(Dispatchers.IO) {
            val response: Response<DrinkGeneral> = api.getMarketDataListBegin()

            response.body()?.drinkList ?: emptyList()
        }

    }

    suspend fun getCryptoDataFromApiByName(name: String): List<Drink> {
        return withContext(Dispatchers.IO) {

            val response = api.getDrinkByName(name)

            response.body()?.drinkList ?: emptyList()
        }

    }

    suspend fun getItemDetailFromId(id: String): List<DrinkDetail> {
        return withContext(Dispatchers.IO) {
            val response = api.getDrinkById(id)
            response.body()?.drinkItemList ?: emptyList()
        }
    }


}
