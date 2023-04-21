package com.saulblanco.drinkdataapp.data.model.network

import com.saulblanco.drinkdataapp.data.model.Drink
import com.saulblanco.drinkdataapp.data.model.DrinkDetail
import com.saulblanco.drinkdataapp.data.model.DrinkDetailItem
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
    suspend fun getDrinkListByCategory(category:String):List<Drink>{
        return withContext(Dispatchers.IO){
            val response = api.getDrinkListByCategory(category)
            response.body()?.drinkList?: emptyList()
        }

    }

    suspend fun getDrinkListByAlcoholic(alcoholic:String):List<Drink>{
        return withContext(Dispatchers.IO){
            val response = api.getDrinkListByAlcoholic(alcoholic)
            response.body()?.drinkList?: emptyList()
        }

    }

    suspend fun getDrinkListByGlassType(glassType:String):List<Drink>{
        return withContext(Dispatchers.IO){
            val response = api.getDrinkListByGlassType(glassType)
            response.body()?.drinkList?: emptyList()
        }

    }

    suspend fun getRandomDrink(): String{
        return withContext(Dispatchers.IO){
            val response= api.getRandomDrink()
            response.body()?.drinkList?.get(0)?.id ?: ""
        }
    }

}
