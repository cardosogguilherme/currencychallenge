package com.guilhermecardoso.currencychallenge.exchangeDashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.guilhermecardoso.currencychallenge.common.BaseViewModel
import com.guilhermecardoso.currencychallenge.data.model.ExchangeRate
import com.guilhermecardoso.currencychallenge.data.repository.ExchangeRepository
import kotlinx.coroutines.launch

class ExchangeDashboardViewModel(private val exchangeRepository: ExchangeRepository): BaseViewModel() {
    val ratesLiveData = MutableLiveData<List<ExchangeRate>>()

    fun fetchRates(source: String = "USD") {
        viewModelScope.launch {
            dataLoading.postValue(true)
            ratesLiveData.postValue(exchangeRepository.getExchangeRates(source)).also { dataLoading.postValue(false) }
        }

    }
}