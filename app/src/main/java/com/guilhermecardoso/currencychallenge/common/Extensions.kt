package com.guilhermecardoso.currencychallenge.common

import android.view.View

fun Any.TAG(): String = this.javaClass.simpleName

fun View?.visibleIf(condition: Boolean, shouldBeGone: Boolean = false) {
    when {
        condition -> {
            this?.visibility = View.VISIBLE
        }
        shouldBeGone -> {
            this?.visibility = View.GONE
        }
        else -> {
            this?.visibility = View.INVISIBLE
        }
    }
}