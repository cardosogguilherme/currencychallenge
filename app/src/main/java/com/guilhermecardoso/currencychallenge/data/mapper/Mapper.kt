package com.guilhermecardoso.currencychallenge.data.mapper

import com.guilhermecardoso.currencychallenge.data.model.DBExchangeRate
import com.guilhermecardoso.currencychallenge.data.model.ExchangeRate
import com.guilhermecardoso.currencychallenge.data.model.NetworkRate

interface Mapper<I, O> {
    fun map(input: I): O
}

// Non-nullable to Non-nullable
fun <I, O> mapList(input: List<I>, mapSingle: (I) -> O): List<O> {
    return input.map { mapSingle(it) }
}

// Nullable to Non-nullable
fun <I, O> mapNullInputList(input: List<I>?, mapSingle: (I) -> O): List<O> {
    return input?.map { mapSingle(it) } ?: emptyList()
}

// Non-nullable to Nullable
fun <I, O> mapNullOutputList(input: List<I>, mapSingle: (I) -> O): List<O>? {
    return if (input.isEmpty()) null else input.map { mapSingle(it) }
}

fun NetworkRate.mapRateDTO(): List<ExchangeRate> =
    quotes.map { ExchangeRate(
        timestamp = timestamp,
        source = source,
        quoteName = it.key.removePrefix(source),
        rate = it.value
    ) }

fun ExchangeRate.mapToDB(): DBExchangeRate =
    DBExchangeRate(
        timeStamp = this.timestamp,
        name = this.quoteName,
        rate = this.rate,
        source = this.source
    )

fun DBExchangeRate.mapToModel(): ExchangeRate =
    ExchangeRate(
        timestamp = this.timeStamp,
        quoteName = this.name,
        rate = this.rate,
        source = this.source
    )