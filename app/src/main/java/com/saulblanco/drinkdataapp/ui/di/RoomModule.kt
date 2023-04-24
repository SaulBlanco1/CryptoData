package com.saulblanco.drinkdataapp.ui.di

import android.content.Context
import androidx.room.Room
import com.saulblanco.drinkdataapp.data.database.DrinkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DRINK_DATABASE_NAME="drink_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DrinkDatabase::class.java,DRINK_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDrinkDao(db:DrinkDatabase)= db.getDrinkDao()

}