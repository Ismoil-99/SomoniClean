package com.example.somoni.presenetation.uimain.partNbt.adapternbt

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.somoni.R
import com.example.somoni.data.model.TransferNbt
import kotlinx.android.synthetic.main.list_nbt_currency.view.*

class AdapterNbtCurrency : RecyclerView.Adapter<AdapterNbtCurrency.CurrencyNbtHolder>() {
    private var getListCurrency = emptyList<TransferNbt>()

    fun getCurrencyNbt(listCurrency: List<TransferNbt>) {
        getListCurrency = listCurrency
        notifyDataSetChanged()
    }

    class CurrencyNbtHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyNbtHolder {
        return CurrencyNbtHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_nbt_currency, parent, false)
        )
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CurrencyNbtHolder, position: Int) {
        holder.itemView.currency_name.text = getListCurrency[position].name
        holder.itemView.currency_full_name.text = getListCurrency[position].full_name
        holder.itemView.value_currency.text = "${String.format("%.3f", getListCurrency[position].value.toDouble())} TJS"
        holder.itemView.flag_currency.load(getListCurrency[position].flag) {
            crossfade(true)
            placeholder(R.drawable.ic_refresh)
        }
    }
    override fun getItemCount(): Int {
        return getListCurrency.size
    }
}