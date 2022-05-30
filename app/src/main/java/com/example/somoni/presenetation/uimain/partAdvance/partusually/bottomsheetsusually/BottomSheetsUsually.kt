package com.example.somoni.presenetation.uimain.partAdvance.partusually.bottomsheetsusually

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.somoni.R
import com.example.somoni.data.model.Currency
import com.example.somoni.databinding.BottomSheetsUsuallyBinding
import com.example.somoni.extensions.SomoniApp
import com.example.somoni.extensions.base.BaseBottomSheet
import com.example.somoni.extensions.utils.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetsUsually : BaseBottomSheet<BottomSheetsUsuallyBinding>(R.layout.bottom_sheets_usually) {
    override val binding by viewBinding(BottomSheetsUsuallyBinding::bind)
    val getOptionCurrency = SomoniApp.sharedPreferences.getInt(CURRENCY_TYPE, 0)
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
        val icons = arguments?.getString(NAME_ICON)
        val getCurrenctValue = arguments?.getString(NAME_CURRENCY_VALUE)
        val colors = intArrayOf(Color.parseColor(getColor1), Color.parseColor(getColor2))
        val gd = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM, colors
        )
        binding.icons.load(icons){
            crossfade(true)
            placeholder(R.drawable.ic_refresh)
        }
        gd.cornerRadius = 0f
        binding.colorsGradient.background = gd
        when(getOptionCurrency){
            0 -> getValue(values, 1,getCurrenctValue)
            1 ->  getValue(values, 0,getCurrenctValue)
            2 ->  getValue(values,2,getCurrenctValue)
            3 -> getValue(values,3,getCurrenctValue)
        }
    }
    private fun getValue(values: ArrayList<Currency>, getPosition: Int,getCurrent:String?) {
        when(getPosition){
            0 -> {
                binding.buyValueCurrencyUsa.text = values[getPosition!!].buy_value
                binding.saleValueCurrencyUsa.text = values[getPosition!!].sell_value
                binding.nameBanks.text = getCurrent
            }
            1 ->{
                binding.buyValueCurrencyRus.text = values[getPosition!!].buy_value
                binding.saleCurrencyRus.text = values[getPosition!!].sell_value
                binding.nameBanks.text = getCurrent
            }
            2 -> {
                binding.buyValueCurrencyEuro.text = values[getPosition!!].buy_value
                binding.saleValueCurrencyEuro.text = values[getPosition!!].sell_value
                binding.nameBanks.text = getCurrent
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = 600
        }
    }
}