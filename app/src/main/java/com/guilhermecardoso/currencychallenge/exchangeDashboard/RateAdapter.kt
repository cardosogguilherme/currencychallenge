package com.guilhermecardoso.currencychallenge.exchangeDashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecardoso.currencychallenge.data.model.ExchangeRate
import com.guilhermecardoso.currencychallenge.databinding.ItemRateBinding

class RateAdapter(private val rateList: List<ExchangeRate>, var amount: Float): RecyclerView.Adapter<ExchangeRateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ExchangeRateViewHolder(ItemRateBinding.inflate(inflater,parent,false))
    }

    override fun getItemCount(): Int = rateList.size

    override fun onBindViewHolder(holder: ExchangeRateViewHolder, position: Int) {
        holder.bind(rateList[position].apply { amount = this@RateAdapter.amount })
    }

    fun updateAmount(amount: Float) {
        this.amount = amount
        notifyDataSetChanged()
    }
}

class ExchangeRateViewHolder (private val binding : ItemRateBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(rate: ExchangeRate){
        binding.rate = rate
        binding.executePendingBindings()
    }

}