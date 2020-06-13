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
import com.guilhermecardoso.currencychallenge.common.TAG
import com.guilhermecardoso.currencychallenge.common.visibleIf
import com.guilhermecardoso.currencychallenge.databinding.ExchangeDashboardFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExchangeDashboardFragment : Fragment() {

    private val viewModel: ExchangeDashboardViewModel by viewModel()
    lateinit var binding: ExchangeDashboardFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.exchange_dashboard_fragment, container, false)
        binding = ExchangeDashboardFragmentBinding.bind(view).apply { lifecycleOwner = this@ExchangeDashboardFragment }

        viewModel.ratesLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(this.TAG(), it.toString())
            binding.recyclerRates.adapter = RateAdapter(it, 0f)
            binding.recyclerRates.layoutManager = GridLayoutManager(context, 3, VERTICAL, false)
        })

        viewModel.dataLoading.observe(viewLifecycleOwner, Observer {
            binding.loading.visibleIf(condition = it, shouldBeGone = true)
        })

        binding.editTextAmount.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val stringAmount = s.toString()
                (binding.recyclerRates.adapter as? RateAdapter)?.updateAmount(if (stringAmount.isEmpty()) 0f else stringAmount.toFloat())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })

        viewModel.fetchRates()

        return view
    }
}