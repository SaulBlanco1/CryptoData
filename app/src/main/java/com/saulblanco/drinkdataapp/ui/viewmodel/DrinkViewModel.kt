package com.saulblanco.drinkdataapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import com.saulblanco.drinkdataapp.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val getFirstData: GetFirstData,
    private val getDrinkDataByName: GetDrinkDataByName,
    private val getDrinkListByCategory: GetDrinkListByCategory,
    private val getDrinkListByAlcoholic: GetDrinkListByAlcoholic,
    private val getDrinkListByGlassType: GetDrinkListByGlassType,
    private val getRandomDrink: GetRandomDrink,
    private val insertFavDrinkIntoFavs: InsertFavDrinkIntoFavs,
    private val deleteFavDrinkFromFavs: DeleteFavDrinkFromFavs,
    private val getFavDrinkList: GetFavDrinkList,
    private val getAllFavs: GetAllDrinkIds
) : ViewModel() {

    val listDrink = MutableLiveData<List<DrinkGeneralDomain>>()
    val isLoading = MutableLiveData<Boolean>()

    private var idRandom: String = ""
    private var idsFromFavsList: List<String> = emptyList()

    //Cuando se crea el ViewModel se lanza la función getFirstData y rellena el rv
    //Con valores iniciales
    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getFirstData()
            if (!result.isNullOrEmpty()) {
                listDrink.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun getIdsFromFav(): List<String> {
        viewModelScope.launch {

            idsFromFavsList = getAllFavs()
        }
        return idsFromFavsList
    }

    fun insertToFav(drinkToFav: DrinkGeneralDomain) {
        viewModelScope.launch {
            insertFavDrinkIntoFavs(drinkToFav)
        }
    }

    fun deleteFromFav(favDrinktoDelete:DrinkGeneralDomain){
        viewModelScope.launch {
            deleteFavDrinkFromFavs(favDrinktoDelete)
        }
    }


    fun setRandomDrink() {
        viewModelScope.launch {
            idRandom = getRandomDrink()
        }


    }

    fun getRandomDrinkId(): String {
        return idRandom
    }


    fun searchDrinkListByCategory(category: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getDrinkListByCategory(category)
            listDrink.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun searchDrinkListByAlcoholic(alcoholic: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getDrinkListByAlcoholic(alcoholic)
            listDrink.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun searchDrinkListByGlassType(glassType: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getDrinkListByGlassType(glassType)
            listDrink.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun searchByName(name: String, categorySelected: String, alcoholic: String, glassType: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getDrinkDataByName(name)
            var drinkListFiltered = filterDrinkList(result, categorySelected, alcoholic, glassType)
            listDrink.postValue(drinkListFiltered)
            isLoading.postValue(false)
        }
    }

    fun filterDrinkList(
        drinkList: List<DrinkGeneralDomain>,
        categorySelected: String,
        alcoholic: String,
        glassType: String,
    ): List<DrinkGeneralDomain> {
        var drinkListFiltered: List<DrinkGeneralDomain> = emptyList()
        drinkList.map {

            //No se selecciona ningún filtro, realiza la búsqueda de la misma lista
            if (categorySelected.equals("Category:") || categorySelected.equals("")) {
                if (alcoholic.equals("Is Alcoholic?:") || alcoholic.equals("")) {
                    if (glassType.equals("Glass Type:") || glassType.equals("")) {
                        Log.i("PRUEBAAHORA", drinkList.toString())
                        return drinkList

                    }
                }
            }

            //Búsqueda  SOLO por categoria
            if (categorySelected.equals(it.category)) {
                if (alcoholic.equals("Is Alcoholic?:") || alcoholic.equals("")) {
                    if (glassType.equals("Glass Type:") || glassType.equals("")) {
                        drinkListFiltered =
                            drinkList.filter { it.category.equals(categorySelected) }
                    }
                }
            }

            //Búsqueda de SOLO si es una bebida alcoholica o no
            if (categorySelected.equals("Category:") || categorySelected.equals("")) {
                if (alcoholic.equals(it.alcoholic)) {
                    if (glassType.equals("Glass Type:") || glassType.equals("")) {
                        drinkListFiltered = drinkList.filter { it.alcoholic.equals(alcoholic) }
                    }
                }
            }

            //Búsqueda  SOLO por tipo de vaso
            if (categorySelected.equals("Category:") || categorySelected.equals("")) {
                if (alcoholic.equals("Is Alcoholic?:") || alcoholic.equals("")) {
                    if (glassType.equals(it.tipoVaso)) {
                        drinkListFiltered = drinkList.filter { it.tipoVaso.equals(glassType) }
                    }
                }
            }


        }

        return drinkListFiltered
    }

}