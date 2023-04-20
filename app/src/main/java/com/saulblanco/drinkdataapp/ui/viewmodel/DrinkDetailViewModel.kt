package com.saulblanco.drinkdataapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulblanco.drinkdataapp.data.model.DrinkDetail
import com.saulblanco.drinkdataapp.domain.GetDrinkDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailViewModel @Inject constructor(
    private val getDrinkDetail: GetDrinkDetail

) : ViewModel() {

    val drinkItem = MutableLiveData<DrinkDetail>()

    fun onCreate(Id: String) {
        viewModelScope.launch {
            val result = getDrinkDetail(Id)

            drinkItem.postValue(result)


        }


    }


}