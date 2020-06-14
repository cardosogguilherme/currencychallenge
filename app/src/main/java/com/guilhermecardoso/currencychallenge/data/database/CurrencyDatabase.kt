package com.guilhermecardoso.currencychallenge.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.guilhermecardoso.currencychallenge.data.model.DBExchangeRate

@Database(entities = [DBExchangeRate::class], exportSchema = false, version = 1)
abstract class CurrencyDatabase: RoomDatabase() {

    abstract fun exchangeRateDAO(): RateDAO

    companion object {
        private var INSTANCE: CurrencyDatabase? = null
        private const val DB_NAME = "exchange_rate_db"

        fun getDatabase(context: Context): CurrencyDatabase? {
            if (INSTANCE == null) {
                synchronized(CurrencyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, CurrencyDatabase::class.java, DB_NAME).build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}