package com.saulblanco.drinkdataapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulblanco.drinkdataapp.domain.usecases.GetDrinkDetail
import com.saulblanco.drinkdataapp.domain.model.DrinkDetailDomain
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import com.saulblanco.drinkdataapp.domain.usecases.DeleteFavDrinkFromFavs
import com.saulblanco.drinkdataapp.domain.usecases.GetAllDrinkIds
import com.saulblanco.drinkdataapp.domain.usecases.InsertFavDrinkIntoFavs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailViewModel @Inject constructor(
    private val getDrinkDetail: GetDrinkDetail,
    private val getAllFavs: GetAllDrinkIds,
    private val insertFavDrinkIntoFavs: InsertFavDrinkIntoFavs,
    private val deleteFavDrinkFromFavs: DeleteFavDrinkFromFavs

    ) : ViewModel() {

    val idsFromFavoriteList = MutableLiveData<List<String>>()
    val drinkItem = MutableLiveData<DrinkDetailDomain>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(Id: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            idsFromFavoriteList.postValue(getAllFavs())
            val result = getDrinkDetail(Id)
            drinkItem.postValue(result)
            isLoading.postValue(false)
        }
    }


    fun insertToFav(drinkToFav: DrinkGeneralDomain) {
        viewModelScope.launch {
            insertFavDrinkIntoFavs(drinkToFav)
        }
    }

    fun deleteFromFav(favDrinktoDelete: DrinkGeneralDomain){
        viewModelScope.launch {
            deleteFavDrinkFromFavs(favDrinktoDelete)
        }
    }

}