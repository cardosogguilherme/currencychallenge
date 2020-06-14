package com.guilhermecardoso.currencychallenge.exchangeDashboard

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.guilhermecardoso.currencychallenge.App
import com.guilhermecardoso.currencychallenge.R
import com.guilhermecardoso.currencychallenge.common.CurrencyTextWatcher
import com.guilhermecardoso.currencychallenge.common.TAG
import com.guilhermecardoso.currencychallenge.common.visibleIf
import com.guilhermecardoso.currencychallenge.databinding.ExchangeDashboardFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.get

class ExchangeDashboardFragment : Fragment(), AdapterView.OnItemSelectedListener {

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

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currencies_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerCurrencies.adapter = adapter

            binding.spinnerCurrencies.onItemSelectedListener = this
            binding.spinnerCurrencies.setSelection(adapter.getPosition("USD"))
        }

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            AlertDialog.Builder(requireContext())
                .setCancelable(true)
                .setTitle("Sorry, something went wrong")
                .setMessage(it)
                .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
                .create().show()
        })

        return view
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        (parent?.getItemAtPosition(position) as? String)?.apply { viewModel.fetchRates(this) }
    }


}