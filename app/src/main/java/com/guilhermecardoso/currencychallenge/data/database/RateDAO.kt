package com.guilhermecardoso.currencychallenge.data.database

import androidx.room.*
import com.guilhermecardoso.currencychallenge.data.model.DBExchangeRate

@Dao
interface RateDAO {
    @Query("select * from exchangerate")
    fun getExchangeRates(): List<DBExchangeRate>

    @Query("select * from exchangerate where source == :source")
    fun getExchangeRatesFrom(source: String): List<DBExchangeRate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExchangeRate(exchangeRate: DBExchangeRate)

    @Update
    fun updateExchangeRate(exchangeRate: DBExchangeRate)

    @Delete
    fun deleteExchangeRate(exchangeRate: DBExchangeRate)
}