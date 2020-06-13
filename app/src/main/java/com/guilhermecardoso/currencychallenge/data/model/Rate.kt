package com.guilhermecardoso.currencychallenge.data.model

data class ExchangeRate (
    val timestamp: Long,
    val source: String,
    val quoteName: String,
    val rate: Float,
    var amount: Float = 0f
)