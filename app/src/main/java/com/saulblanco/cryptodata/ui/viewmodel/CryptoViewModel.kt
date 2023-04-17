package com.saulblanco.cryptodata.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulblanco.cryptodata.domain.GetFirstData
import com.saulblanco.cryptodata.domain.model.CryptoData
import com.saulblanco.cryptodata.ui.view.CryptoAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val getFirstData: GetFirstData
) : ViewModel() {

    val cryptoDataModel = MutableLiveData<CryptoData>()


    fun onCreate(){
        viewModelScope.launch {
            val result = getFirstData()


            if(!result.isNullOrEmpty()){
                cryptoDataModel.postValue(result[0])

            }
        }


    }

}