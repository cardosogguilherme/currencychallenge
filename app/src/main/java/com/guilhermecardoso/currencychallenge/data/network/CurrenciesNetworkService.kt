package com.guilhermecardoso.currencychallenge.data.network

import retrofit2.http.GET

interface CurrenciesNetworkService {
    @GET("/live")
    suspend fun live()

}