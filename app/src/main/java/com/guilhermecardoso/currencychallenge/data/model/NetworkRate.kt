package com.guilhermecardoso.currencychallenge.data.model

import com.google.gson.annotations.SerializedName

data class NetworkRate(
    @SerializedName("success") val success: Boolean,
    @SerializedName("terms") val terms: String,
    @SerializedName("privacy") val privacy: String,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("source") val source: String,
    @SerializedName("quotes") val quotes: HashMap<String, Float>,
    @SerializedName("error") val error: ErrorRate?
)

data class ErrorRate(
    @SerializedName("code") val code: Int,
    @SerializedName("info") val info: String
)