package com.guilhermecardoso.currencychallenge

import android.app.Application
import com.guilhermecardoso.currencychallenge.data.database.databaseModule
import com.guilhermecardoso.currencychallenge.data.network.networkModule
import com.guilhermecardoso.currencychallenge.data.repository.repositoryModule
import com.guilhermecardoso.currencychallenge.exchangeDashboard.exchangeDashboardModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                networkModule,
                exchangeDashboardModule,
                repositoryModule,
                databaseModule
            )
        }
    }
}