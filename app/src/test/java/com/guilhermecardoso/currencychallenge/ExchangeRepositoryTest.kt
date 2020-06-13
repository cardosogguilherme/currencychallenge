package com.guilhermecardoso.currencychallenge

import com.guilhermecardoso.currencychallenge.data.network.CurrenciesNetworkService
import com.guilhermecardoso.currencychallenge.data.network.networkModule
import com.guilhermecardoso.currencychallenge.data.repository.ExchangeRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject

class ExchangeRepositoryTest : KoinTest {

    @Test
    fun testGetExchangeRates() {
        startKoin { modules(networkModule) }
        val repo = ExchangeRepositoryImpl(get())

        CoroutineScope(Dispatchers.IO).launch {
            val exchanges = repo.getExchangeRates("USD")
            Assert.assertNotNull(exchanges) //should not be null
            Assert.assertEquals(false, exchanges.isEmpty()) //we should get the exchanges
            Assert.assertEquals(null, exchanges.firstOrNull { it.rate < 0 }) //no negative rate
            Assert.assertEquals(null, exchanges.firstOrNull { it.rate == 0f }) //no 0 rates
        }
    }
}