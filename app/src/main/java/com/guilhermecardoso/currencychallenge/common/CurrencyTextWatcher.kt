package com.guilhermecardoso.currencychallenge.common

import android.text.Editable
import android.text.TextWatcher

class CurrencyTextWatcher(private val processText: ((String) -> Unit)): TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        processText.invoke(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}