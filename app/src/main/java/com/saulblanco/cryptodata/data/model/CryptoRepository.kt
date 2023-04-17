package com.saulblanco.cryptodata.data.model

import com.saulblanco.cryptodata.data.model.network.CryptoService
import com.saulblanco.cryptodata.domain.model.CryptoData
import com.saulblanco.cryptodata.domain.model.toDomain
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val api:CryptoService
) {
    suspend fun getAllCryptoDataFromApi():List<CryptoData>{
        val response: List<CryptoListModel> = api.getCryptoData()
        return response.map{it.toDomain()}
    }

}