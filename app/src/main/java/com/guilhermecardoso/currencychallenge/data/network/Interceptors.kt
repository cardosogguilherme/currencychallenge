package com.guilhermecardoso.currencychallenge.data.network

import com.guilhermecardoso.currencychallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val url = request().url().newBuilder()
            .addQueryParameter("access_key", BuildConfig.API_KEY).build()
        proceed(request().newBuilder().url(url).build())
    }
}