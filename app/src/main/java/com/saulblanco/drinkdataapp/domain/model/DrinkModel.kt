package com.saulblanco.drinkdataapp.domain.model

import com.saulblanco.drinkdataapp.data.model.Drink
import com.saulblanco.drinkdataapp.data.model.DrinkGeneral


data class DrinkDomain(
    val drinkListModel: List<Drink>,

)

data class DrinkGeneralDomain(
    val id: String,
    val name: String,
    val image:String
)

fun Drink.toDomain()=DrinkGeneralDomain(id,name,image)
