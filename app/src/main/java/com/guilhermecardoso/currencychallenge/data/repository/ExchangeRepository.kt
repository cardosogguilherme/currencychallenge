package com.guilhermecardoso.currencychallenge.data.repository

import com.guilhermecardoso.currencychallenge.data.database.CurrencyDatabase
import com.guilhermecardoso.currencychallenge.data.mapper.mapRateDTO
import com.guilhermecardoso.currencychallenge.data.mapper.mapToDB
import com.guilhermecardoso.currencychallenge.data.mapper.mapToModel
import com.guilhermecardoso.currencychallenge.data.model.ExchangeRate
import com.guilhermecardoso.currencychallenge.data.network.CurrenciesNetworkService
import java.util.*
import java.util.concurrent.TimeUnit

interface ExchangeRepository {
    suspend fun getExchangeRates(source: String): List<ExchangeRate>
}

class ExchangeRepositoryImpl(
    private val exchangeService: CurrenciesNetworkService,
    private val exchangeDatabase: CurrencyDatabase
): ExchangeRepository {
    private var timeStamp = Calendar.getInstance().time


    override suspend fun getExchangeRates(source: String): List<ExchangeRate> {
        val resultFromDatabase = exchangeDatabase.exchangeRateDAO().getExchangeRatesFrom(source)

        // #1 If there is more than 30 min, we can refresh directly
        // #1.1 However, if we don't have it, we must update anyway
        return if (TimeUnit.MILLISECONDS.toMinutes(Calendar.getInstance().time.time - timeStamp.time) >= 30 ||
            resultFromDatabase.isEmpty()) {
            timeStamp.time = Calendar.getInstance().time.time
            // #2 Download from web, update database then return

            exchangeService
                .live()
                .await()
                .mapRateDTO()
                .apply { forEach { exchangeDatabase.exchangeRateDAO().insertExchangeRate(it.mapToDB()) } }
        } else {
            // #3 Return directly from database
            resultFromDatabase.map { it.mapToModel() }
        }
    }
}

