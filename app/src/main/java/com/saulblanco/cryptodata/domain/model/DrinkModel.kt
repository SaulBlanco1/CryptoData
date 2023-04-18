package com.saulblanco.cryptodata.domain.model

import com.google.gson.annotations.SerializedName
import com.saulblanco.cryptodata.data.model.Drink
import com.saulblanco.cryptodata.data.model.DrinkGeneral


data class DrinkModel(
    val drinkList: List<Drink>,

)

data class drink(
    val id: String,
    val name: String
)

fun DrinkGeneral.toDomain()=DrinkModel(drinkList)
