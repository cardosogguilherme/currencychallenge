package com.guilhermecardoso.currencychallenge.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val empty = MutableLiveData<Boolean>().apply { value = false }

    val dataLoading = MutableLiveData<Boolean>().apply { value = false }

    val errorMessage = MutableLiveData<String>()
}