package com.saulblanco.drinkdataapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulblanco.drinkdataapp.data.model.Drink
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


    fun onCreate() {
        viewModelScope.launch {
            val result = getFirstData()
            if (!result.isNullOrEmpty()) {
                listDrink.postValue(result)
            }
        }


    }

    fun searchByName(name: String) {
        viewModelScope.launch {

            val result = getDrinkDataByName(name)
            if (!result.isNullOrEmpty()) {
                listDrink.postValue(result)
            }
        }
    }




}