package com.saulblanco.drinkdataapp.data.database.dao

import androidx.room.*
import com.saulblanco.drinkdataapp.data.database.entities.DrinkEntity

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_table ORDER BY name DESC")
    suspend fun getAllFavDrinks():List<DrinkEntity>

    @Query("SELECT id FROM drink_table")
    suspend fun getAllIds():List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavDrink(drink:DrinkEntity)

    @Delete
    suspend fun deleteFavDrink(drink: DrinkEntity)

    @Query("DELETE FROM drink_table")
    suspend fun deleteAllFavDrinks()


}