package com.saulblanco.cryptodata.data.model.network

import com.saulblanco.cryptodata.data.model.CryptoListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptoService @Inject constructor(private val api: CryptoApiClient) {

    suspend fun getCryptoData(): List<CryptoListModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getMarketDataList()

            response.body() ?: emptyList()
        }
    }
}