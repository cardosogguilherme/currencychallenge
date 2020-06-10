package com.guilhermecardoso.currencychallenge.data

import com.guilhermecardoso.currencychallenge.BuildConfig
import com.guilhermecardoso.currencychallenge.data.network.CurrenciesNetworkService
import com.guilhermecardoso.currencychallenge.data.network.HeaderInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get()) }
    single { createNetworkApi(get()) }
}

fun createNetworkApi(retrofit: Retrofit): CurrenciesNetworkService = retrofit.create(CurrenciesNetworkService::class.java)

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BuildConfig.API_ENDPOINT)
        .client(okHttpClient)
        .build()

fun createOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
        .addInterceptor(HeaderInterceptor())
        .build()