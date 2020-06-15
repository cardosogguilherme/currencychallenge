package com.guilhermecardoso.currencychallenge.exchangeDashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.guilhermecardoso.currencychallenge.common.BaseViewModel
import com.guilhermecardoso.currencychallenge.data.model.ExchangeRate
import com.guilhermecardoso.currencychallenge.data.repository.ExchangeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ExchangeDashboardViewModel(private val exchangeRepository: ExchangeRepository): BaseViewModel() {
    val ratesLiveData = MutableLiveData<List<ExchangeRate>>()

    fun fetchRates(source: String = "USD", force: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dataLoading.postValue(true)
                ratesLiveData.postValue(exchangeRepository.getExchangeRates(source, force).sortedBy { exchangeRate: ExchangeRate -> exchangeRate.quoteName })
            } catch (exception: Exception) {
                errorMessage.postValue(exception.message)
            } finally {
                dataLoading.postValue(false)
            }
        }

    }
}