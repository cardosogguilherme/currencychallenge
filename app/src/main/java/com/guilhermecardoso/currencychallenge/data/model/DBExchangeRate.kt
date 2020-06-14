package com.guilhermecardoso.currencychallenge.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExchangeRate")
data class DBExchangeRate(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "rate") val rate: Float,
    @ColumnInfo(name = "timeStamp") val timeStamp: Long,
    @ColumnInfo(name = "source") val source: String,
    @ColumnInfo(name = "name") val name: String
)