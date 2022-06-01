package com.example.somoni.presenetation.uimain.partNbt.bottomsheet

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.BottomChangeCurrencyBinding
import com.example.somoni.extensions.base.BaseBottomSheet

class BottomSheetsChangeCurrency(val onItemClick :(Int) -> Unit) : BaseBottomSheet<BottomChangeCurrencyBinding>(R.layout.bottom_change_currency) {
    override val binding by viewBinding(BottomChangeCurrencyBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.euroCurrency?.setOnClickListener {
            onItemClick!!.invoke(1)
            dismiss()
        }
        binding.rusCurrency?.setOnClickListener {
            onItemClick!!.invoke(2)
            dismiss()
        }
        binding.usdCurrency?.setOnClickListener {
            onItemClick!!.invoke(3)
            dismiss()
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}