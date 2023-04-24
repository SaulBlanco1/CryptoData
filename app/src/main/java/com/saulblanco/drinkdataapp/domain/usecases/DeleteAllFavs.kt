package com.saulblanco.drinkdataapp.domain.usecases

import com.saulblanco.drinkdataapp.data.DrinkRepository
import javax.inject.Inject

class DeleteAllFavs @Inject constructor(
    private val repository: DrinkRepository
){

    suspend operator fun invoke() {

        repository.deleteAllFavs()
    }
}