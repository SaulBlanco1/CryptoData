package com.saulblanco.drinkdataapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saulblanco.drinkdataapp.data.database.dao.DrinkDao
import com.saulblanco.drinkdataapp.data.database.entities.DrinkEntity

@Database(entities = [DrinkEntity::class], version = 1)
abstract class DrinkDatabase : RoomDatabase() {

    abstract fun getDrinkDao(): DrinkDao

}