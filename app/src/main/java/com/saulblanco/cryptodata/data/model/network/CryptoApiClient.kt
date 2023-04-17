package com.saulblanco.cryptodata.data.model.network



import com.saulblanco.cryptodata.data.model.CryptoListModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoApiClient {

    //Recojo los datos de las 100 primeras coins
    @GET("/v1/cryptocurrency/map?start=1&limit=100&CMC_PRO_API_KEY=72464817-3daf-4ed8-969e-7133c88a6827")
    suspend fun getMarketDataList(): Response<List<CryptoListModel>>


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

