package com.guilhermecardoso.currencychallenge.exchangeDashboard

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exchangeDashboardModule = module {
    viewModel { ExchangeDashboardViewModel(get()) }
}