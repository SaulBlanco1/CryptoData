package com.saulblanco.cryptodata.domain

import com.saulblanco.cryptodata.data.model.CryptoRepository
import com.saulblanco.cryptodata.domain.model.CryptoData
import javax.inject.Inject

class GetFirstData @Inject constructor(
    private val repository: CryptoRepository
){

    suspend operator fun invoke():List<CryptoData>? {
        return repository.getAllCryptoDataFromApi()
    }
}