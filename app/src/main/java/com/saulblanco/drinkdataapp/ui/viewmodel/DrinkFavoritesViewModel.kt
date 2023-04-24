package com.saulblanco.drinkdataapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import com.saulblanco.drinkdataapp.domain.usecases.DeleteAllFavs
import com.saulblanco.drinkdataapp.domain.usecases.DeleteFavDrinkFromFavs
import com.saulblanco.drinkdataapp.domain.usecases.GetFavDrinkList
import com.saulblanco.drinkdataapp.domain.usecases.InsertFavDrinkIntoFavs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkFavoritesViewModel @Inject constructor(
    private val getFavDrinkList: GetFavDrinkList,
    private val deleteFavDrinkFromFavs: DeleteFavDrinkFromFavs,
    private val deleteAllFavorites: DeleteAllFavs
): ViewModel() {

    //OJOJOJO -- PUEDE QUE TENGA QUE CONVERTIR A LISTA PORQUE DEVUELVE UN SOLO DRINKGENERALDOMAIN
    val listFavDrink = MutableLiveData<List<DrinkGeneralDomain>>()

    fun onCreate(){
        viewModelScope.launch {
            val result= getFavDrinkList()
            if(!result.isEmpty()){
                listFavDrink.postValue(result)
            }
        }

    }

    fun deleteFav(favDrinktoDelete:DrinkGeneralDomain){
        viewModelScope.launch {
            deleteFavDrinkFromFavs(favDrinktoDelete)
            val drinkListUpdated=getFavDrinkList()
            listFavDrink.postValue(drinkListUpdated)
        }
    }

    fun deleteAllFavs(){
        viewModelScope.launch {
            deleteAllFavorites()
            val drinkListUpdated=getFavDrinkList()
            listFavDrink.postValue(drinkListUpdated)


        }
    }



}