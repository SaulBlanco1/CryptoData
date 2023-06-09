package com.saulblanco.drinkdataapp.data.model

import com.google.gson.annotations.SerializedName

data class DrinkDetailItem(
    @SerializedName("drinks") val drinkItemList: List<DrinkDetail>,

)
//Nullables porque en el filtrado solo recoge nombre e imagen de la llamada a la API
data class DrinkDetail(
    @SerializedName("idDrink") val id:String,
    @SerializedName("strDrink") val name : String,
    @SerializedName("strCategory") val category : String,
    @SerializedName("strAlcoholic") val alcoholic : String,
    @SerializedName("strInstructions") val instr : String,
    @SerializedName("strIngredient1") val ing1 : String?,
    @SerializedName("strIngredient2") val ing2 : String?,
    @SerializedName("strIngredient3") val ing3 : String?,
    @SerializedName("strDrinkThumb") val imageDetail : String,
    @SerializedName("strGlass") val glassType:String

)