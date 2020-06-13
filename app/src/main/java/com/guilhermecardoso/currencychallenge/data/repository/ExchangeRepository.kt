package com.guilhermecardoso.currencychallenge.data.repository

import com.guilhermecardoso.currencychallenge.data.mapper.mapRateDTO
import com.guilhermecardoso.currencychallenge.data.model.ExchangeRate
import com.guilhermecardoso.currencychallenge.data.network.CurrenciesNetworkService

interface ExchangeRepository {
    suspend fun getExchangeRates(source: String): List<ExchangeRate>
}

class ExchangeRepositoryImpl(private val exchangeService: CurrenciesNetworkService): ExchangeRepository {

    override suspend fun getExchangeRates(source: String): List<ExchangeRate> {
        return exchangeService.live().await().mapRateDTO()
    }
}

