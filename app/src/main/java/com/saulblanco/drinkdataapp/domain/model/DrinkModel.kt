package com.saulblanco.drinkdataapp.domain.model

import com.saulblanco.drinkdataapp.data.database.entities.DrinkEntity
import com.saulblanco.drinkdataapp.data.model.Drink
import com.saulblanco.drinkdataapp.data.model.DrinkGeneral


data class DrinkDomain(
    val drinkListModel: List<Drink>,

)

data class DrinkGeneralDomain(
    val id: String,
    val name: String,
    val image:String,
    val alcoholic:String?,
    val tipoVaso:String?,
    val category:String?
)

fun Drink.toDomain()=DrinkGeneralDomain(id,name,image,alcoholic,tipoVaso, category)
fun DrinkEntity.toDomain()=DrinkGeneralDomain(id,name,image,alcoholic,tipoVaso,category)