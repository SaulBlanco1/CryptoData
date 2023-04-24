package com.saulblanco.drinkdataapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saulblanco.drinkdataapp.data.database.entities.DrinkEntity

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_table ORDER BY name DESC")
    suspend fun getAllFavDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavDrink(drink:DrinkEntity)

    @Query("DELETE FROM drink_table")
    suspend fun deleteAllFavDrinks()


}