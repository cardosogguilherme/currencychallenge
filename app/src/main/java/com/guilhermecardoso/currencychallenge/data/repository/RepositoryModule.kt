package com.guilhermecardoso.currencychallenge.data.repository

import org.koin.dsl.module

val repositoryModule = module {
    single<ExchangeRepository> { ExchangeRepositoryImpl(get(), get()) }
}