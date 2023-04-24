package com.saulblanco.drinkdataapp.data

import com.saulblanco.drinkdataapp.data.database.dao.DrinkDao
import com.saulblanco.drinkdataapp.data.database.entities.DrinkEntity
import com.saulblanco.drinkdataapp.data.network.DrinkService
import com.saulblanco.drinkdataapp.domain.model.DrinkDetailDomain
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import com.saulblanco.drinkdataapp.domain.model.toDomain
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val api: DrinkService,
    private val drinkDao: DrinkDao
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

    suspend fun getDrinkListByCategory(category: String): List<DrinkGeneralDomain> {
        val response = api.getDrinkListByCategory(category)
        return response.map { it.toDomain() }
    }

    suspend fun getDrinkListByAlcoholic(alcoholic: String): List<DrinkGeneralDomain> {
        val response = api.getDrinkListByAlcoholic(alcoholic)
        return response.map { it.toDomain() }
    }

    suspend fun getDrinkListByGlassType(glassType: String): List<DrinkGeneralDomain> {
        val response = api.getDrinkListByGlassType(glassType)
        return response.map { it.toDomain() }
    }

    suspend fun getRandomDrink(): String {
        val response = api.getRandomDrink()
        return response
    }

    suspend fun getFavDrinkListFromDB(): List<DrinkGeneralDomain> {
        val response = drinkDao.getAllFavDrinks()
        return response.map { it.toDomain() }

    }

    suspend fun insertFavDrink(drinkFav: DrinkEntity) {
        drinkDao.insertFavDrink(drinkFav)
    }

    suspend fun getAllIdsFromFavs(): List<String> {
        return drinkDao.getAllIds()
    }

    suspend fun deleteFavDrink(drinkFav: DrinkEntity) {
        drinkDao.deleteFavDrink(drinkFav)
    }

    suspend fun deleteAllFavs() {
        drinkDao.deleteAllFavDrinks()
    }


}