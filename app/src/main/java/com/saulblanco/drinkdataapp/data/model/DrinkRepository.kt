package com.saulblanco.drinkdataapp.data.model

import com.saulblanco.drinkdataapp.data.model.network.DrinkService
import com.saulblanco.drinkdataapp.domain.model.DrinkDetailDomain
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import com.saulblanco.drinkdataapp.domain.model.toDomain
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val api: DrinkService
) {
    suspend fun getAllDrinkDataFromApi(): List<DrinkGeneralDomain> {
        val response = api.getCryptoDataFromApi()
        return response.map { it.toDomain() }
    }

    suspend fun getDrinkDataByName(name: String): List<DrinkGeneralDomain> {
        val response = api.getCryptoDataFromApiByName(name)
        return response.map { it.toDomain() }
    }

    suspend fun getItemDetail(id: String): List<DrinkDetailDomain> {
        val response = api.getItemDetailFromId(id)
        return response.map { it.toDomain() }
    }

    suspend fun getDrinkListByCategory(category:String): List<DrinkGeneralDomain>{
        val response = api.getDrinkListByCategory(category)
        return response.map {it.toDomain()}
    }

    suspend fun getDrinkListByAlcoholic(alcoholic:String): List<DrinkGeneralDomain>{
        val response = api.getDrinkListByAlcoholic(alcoholic)
        return response.map {it.toDomain()}
    }

    suspend fun getDrinkListByGlassType(glassType:String): List<DrinkGeneralDomain>{
        val response = api.getDrinkListByGlassType(glassType)
        return response.map {it.toDomain()}
    }

    suspend fun getRandomDrink():String{
        val response= api.getRandomDrink()
        return response
    }

}