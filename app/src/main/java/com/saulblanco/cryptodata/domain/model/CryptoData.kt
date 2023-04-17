package com.saulblanco.cryptodata.domain.model

import com.google.gson.annotations.SerializedName
import com.saulblanco.cryptodata.data.model.CryptoListModel


data class CryptoData(
    val name: String

)

fun CryptoListModel.toDomain() = CryptoData(name)
