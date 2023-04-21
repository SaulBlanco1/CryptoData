package com.saulblanco.drinkdataapp.data.model.network



import com.saulblanco.drinkdataapp.data.model.DrinkDetailItem
import com.saulblanco.drinkdataapp.data.model.DrinkGeneral
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApiClient {


    //Recojo datos para inicializar la App con info en el rv
    @GET("search.php?s=a")
    suspend fun getMarketDataListBegin(): Response<DrinkGeneral>

    @GET("search.php?")
    suspend fun getDrinkByName(@Query("s") s:String): Response<DrinkGeneral>

    @GET("lookup.php?")
    suspend fun getDrinkById(@Query("i") i : String): Response<DrinkDetailItem>

    @GET("filter.php?")
    suspend fun getDrinkListByCategory(@Query("c") c:String):Response<DrinkGeneral>

    @GET("filter.php?")
    suspend fun getDrinkListByAlcoholic(@Query("a") a:String):Response<DrinkGeneral>

    @GET("filter.php?")
    suspend fun getDrinkListByGlassType(@Query("g") g:String):Response<DrinkGeneral>

    @GET("random.php")
    suspend fun getRandomDrink():Response<DrinkGeneral>

}

