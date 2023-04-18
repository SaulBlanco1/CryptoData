package com.saulblanco.cryptodata.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulblanco.cryptodata.data.model.Drink
import com.saulblanco.cryptodata.domain.GetFirstData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val getFirstData: GetFirstData,
) : ViewModel() {

    val listDrink = MutableLiveData<List<Drink>>()




    fun onCreate(){
        viewModelScope.launch {
            val result= getFirstData()

            if(!result.isNullOrEmpty()){
                listDrink.postValue(result)
            }
        }


    }



}