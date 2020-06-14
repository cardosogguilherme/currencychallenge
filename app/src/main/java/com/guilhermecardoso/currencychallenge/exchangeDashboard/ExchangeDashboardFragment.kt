package com.guilhermecardoso.currencychallenge.exchangeDashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.guilhermecardoso.currencychallenge.R
import com.guilhermecardoso.currencychallenge.common.CurrencyTextWatcher
import com.guilhermecardoso.currencychallenge.common.TAG
import com.guilhermecardoso.currencychallenge.common.visibleIf
import com.guilhermecardoso.currencychallenge.databinding.ExchangeDashboardFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExchangeDashboardFragment : Fragment() {

    private val viewModel: ExchangeDashboardViewModel by viewModel()
    lateinit var binding: ExchangeDashboardFragmentBinding
    private var adapter: RateAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.exchange_dashboard_fragment, container, false)
        binding = ExchangeDashboardFragmentBinding.bind(view).apply { lifecycleOwner = this@ExchangeDashboardFragment }


        viewModel.ratesLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(this.TAG(), it.toString())
            if (adapter == null) {
                adapter =  RateAdapter(it, 0f)
            } else {
                adapter?.notifyDataSetChanged()
            }
            binding.recyclerRates.adapter = adapter
            binding.recyclerRates.layoutManager = GridLayoutManager(context, 3, VERTICAL, false)
        })

        viewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            binding.loading.visibleIf(it, shouldBeGone = true)
        })

        binding.editTextAmount.addTextChangedListener(CurrencyTextWatcher { stringAmount ->
            adapter?.updateAmount(if (stringAmount.isEmpty()) 0f else stringAmount.toFloat())
        })

        viewModel.fetchRates()

        return view
    }
}