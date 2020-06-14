package com.guilhermecardoso.currencychallenge.data.network

import com.guilhermecardoso.currencychallenge.data.model.NetworkRate
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesNetworkService {
    @GET("/live")
    fun live(@Query("source") source: String): Deferred<NetworkRate>

    @GET("/historical")
    fun historical(@Query("date") date: String): Deferred<NetworkRate>

}