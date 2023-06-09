package com.saulblanco.drinkdataapp.data.model

import com.google.gson.annotations.SerializedName

data class DrinkGeneral(
    @SerializedName("drinks") val drinkList: List<Drink>
)

data class Drink(
    @SerializedName("idDrink") val id: String,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkThumb") val image:String,
    @SerializedName("strAlcoholic") val alcoholic:String?,
    @SerializedName("strGlass") val tipoVaso:String?,
    @SerializedName("strCategory") val category:String?
)
