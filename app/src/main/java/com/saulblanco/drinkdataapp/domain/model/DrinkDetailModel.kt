package com.saulblanco.drinkdataapp.domain.model

import com.google.gson.annotations.SerializedName
import com.saulblanco.drinkdataapp.data.model.DrinkDetail
import com.saulblanco.drinkdataapp.data.model.DrinkDetailItem


data class DrinkItemDomain(
    val drinkItemList: List<DrinkDetail>,

    )

data class DrinkDetailDomain(
    val id:String,
    val name: String,
    val category: String,
    val alcoholic: String,
    val instr: String,
    val ing1: String?,
    val ing2: String?,
    val ing3: String?,
    val imageDetail: String,
    val glassType:String

)

fun DrinkDetail.toDomain() = DrinkDetailDomain(id,name,category,alcoholic,instr,ing1, ing2, ing3, imageDetail,glassType)