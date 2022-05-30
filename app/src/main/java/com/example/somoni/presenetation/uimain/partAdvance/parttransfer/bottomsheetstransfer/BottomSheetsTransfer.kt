package com.example.somoni.presenetation.uimain.partAdvance.parttransfer.bottomsheetstransfer

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.somoni.R
import com.example.somoni.data.model.Currency
import com.example.somoni.databinding.BottomSheetsTransferBinding
import com.example.somoni.extensions.base.BaseBottomSheet
import com.example.somoni.extensions.utils.*

class BottomSheetsTransfer : BaseBottomSheet<BottomSheetsTransferBinding>(R.layout.bottom_sheets_transfer) {
    override val binding by viewBinding(BottomSheetsTransferBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closeSheets.setOnClickListener {
            dismiss()
        }
        showValue()
    }
    private fun showValue() {
        val getColor1 = arguments?.getString(NAME_COLOR_ONE)
        val getColor2 = arguments?.getString(NAME_COLOR_SECOND)
        val values = arguments?.getSerializable(CurrencyINFO) as ArrayList<Currency>
        val position = arguments?.getInt(NAME_POSITION)
        val getCurrenctValue = arguments?.getString(NAME_CURRENCY_VALUE)
        val icons = arguments?.getString(NAME_ICON)
        val colors = intArrayOf(Color.parseColor(getColor1), Color.parseColor(getColor2))
        val gd = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM, colors
        )
        gd.cornerRadius = 0f
        binding.colorsGradient.background = gd
        getValue(values,position,getCurrenctValue)
        binding.icons.load(icons){
            crossfade(true)
            placeholder(R.drawable.ic_refresh)
        }
    }
    private fun getValue(values: ArrayList<Currency>, position: Int?, getCurrenctValue: String?) {
        binding.buyValueCurrencyRus.text = values[position!!].buy_value
        binding.saleCurrencyRus.text = values[position!!].sell_value
        binding.nameBanks.text = getCurrenctValue
    }
}