package com.saulblanco.drinkdataapp.domain.model

import com.saulblanco.drinkdataapp.data.model.Drink
import com.saulblanco.drinkdataapp.data.model.DrinkGeneral


data class DrinkModel(
    val drinkList: List<Drink>,

)

data class drink(
    val id: String,
    val name: String
)

fun DrinkGeneral.toDomain()=DrinkModel(drinkList)
