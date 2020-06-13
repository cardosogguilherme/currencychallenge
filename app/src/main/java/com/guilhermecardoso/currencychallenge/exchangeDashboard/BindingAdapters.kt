package com.guilhermecardoso.currencychallenge.exchangeDashboard

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("formatedAmount")
fun TextView.bindFormatedAmount(rate: Float) {
    text = String.format("%.2f", rate)
}