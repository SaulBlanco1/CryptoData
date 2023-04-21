package com.saulblanco.drinkdataapp.ui.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.saulblanco.drinkdataapp.domain.GetDrinkDataByName
import com.saulblanco.drinkdataapp.domain.GetFirstData
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val getFirstData: GetFirstData,
    private val getDrinkDataByName: GetDrinkDataByName
) : ViewModel() {

    val listDrink = MutableLiveData<List<DrinkGeneralDomain>>()

    //Cuando se crea el ViewModel se lanza la función getFirstData y rellena el rv
    //Con valores iniciales
    fun onCreate() {

        viewModelScope.launch {
            val result = getFirstData()
            if (!result.isNullOrEmpty()) {
                listDrink.postValue(result)
            }
        }


    }

    fun searchByName(name: String, categorySelected: String, alcoholic: String, glassType: String) {
        viewModelScope.launch {
            val result = getDrinkDataByName(name)
            var drinkListFiltered = filterDrinkList(result, categorySelected, alcoholic, glassType)
            listDrink.postValue(drinkListFiltered)

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