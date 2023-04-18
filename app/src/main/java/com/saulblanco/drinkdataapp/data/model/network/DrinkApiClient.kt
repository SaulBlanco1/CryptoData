package com.saulblanco.drinkdataapp.data.model.network



import com.saulblanco.drinkdataapp.data.model.DrinkGeneral
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DrinkApiClient {


    //Recojo datos para inicializar la App con info en el rv
    @GET("search.php?s=a")
    suspend fun getMarketDataListBegin(): Response<DrinkGeneral>

    @GET("search.php?")
    suspend fun getDrinkByName(@Query("s") s:String): Response<DrinkGeneral>


//    //Recojo los datos filtrados por nombre
//    @GET("/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=72464817-3daf-4ed8-969e-7133c88a6827&sort=name")
//    suspend fun getMarketDataListFilterbyName(): Response<List<CryptoListModel>>
//
//    //Recojo los datos filtrados por simbolo
//    @GET("/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=72464817-3daf-4ed8-969e-7133c88a6827&sort=symbol")
//    suspend fun getMarketDataListFilterbySymbol(): Response<List<CryptoListModel>>
//
//    //Recojo los datos filtrados por fecha
//    @GET("/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=72464817-3daf-4ed8-969e-7133c88a6827&sort=date_added")
//    suspend fun getMarketDataListFilterByDate(): Response<List<CryptoListModel>>
}

