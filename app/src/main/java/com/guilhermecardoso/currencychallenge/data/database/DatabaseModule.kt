package com.guilhermecardoso.currencychallenge.data.database

import org.koin.dsl.module

val databaseModule = module {
    single { CurrencyDatabase.getDatabase(get()) }
}