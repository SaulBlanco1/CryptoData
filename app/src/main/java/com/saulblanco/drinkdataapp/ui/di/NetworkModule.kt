package com.saulblanco.examplemvvm.di

import com.saulblanco.drinkdataapp.data.network.DrinkApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) // EL alcance que queremos que tenga
object NetworkModule {

    //proveo Retrofit
    @Singleton // Sirve para crear una sola instancia de Retrofit y no tener muchas creadas
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDrinkApiClient(retrofit:Retrofit): DrinkApiClient {
        return retrofit.create(DrinkApiClient::class.java)
    }

}