package com.example.somoni.presenetation.uimain.partNbt.bottomsheet

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.somoni.R
import com.example.somoni.data.model.Currency
import com.example.somoni.data.model.TransferNbt
import com.example.somoni.databinding.BottomSheetsTransferBinding
import com.example.somoni.databinding.BottomSheetsTransfernbtBinding
import com.example.somoni.databinding.BottomSheetsUsuallyBinding
import com.example.somoni.extensions.base.BaseBottomSheet
import com.example.somoni.extensions.utils.*
import com.example.somoni.presenetation.uimain.partNbt.nbtfragment.NbtFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class BottomSheetsNBTTransfer : BaseBottomSheet<BottomSheetsTransfernbtBinding>(R.layout.bottom_sheets_transfernbt) {
    override val binding by viewBinding(BottomSheetsTransfernbtBinding::bind)
    var getUsd : Serializable? = null
    var getRub : Serializable? =  null
    var getEUR : Serializable? =  null
    var changeCurrency = 0
    override fun onStart() {
        super.onStart()
        val sheetContainer = requireView().parent as? ViewGroup ?: return
        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = 600
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding.closeModal.setOnClickListener {
            dismiss()
        }
        getUsd = requireArguments().getSerializable(CURRENCY_USD)
        getRub = requireArguments().getSerializable(CURRENCY_RUS)
        getEUR = requireArguments().getSerializable(CURRENCY_EUR)
        defaultCurrency(getRub as TransferNbt)
        binding.changeCurrency.setOnClickListener {
            val bottomSheet = BottomSheetsChangeCurrency{ num ->
                when(num){
                    1 -> {
                        binding.nameCurrency.text = getString(R.string.lg_euro)
                        binding.currency.text = getString(R.string.name_euro)
                        changeCurrency = num
                        makeConvert(getEUR as TransferNbt,getRub as TransferNbt,getUsd as TransferNbt)
                    }
                    2 -> {
                        binding.nameCurrency?.text = getString(R.string.lg_russian)
                        binding.currency.text = getString(R.string.name_rub)
                        changeCurrency = num
                        makeConvert(getEUR as TransferNbt,getRub as TransferNbt,getUsd as TransferNbt)
                    }
                    3 -> {
                        binding.nameCurrency?.text = getString(R.string.lg_usa)
                        binding.currency.text = getString(R.string.name_usd)
                        changeCurrency = num
                        makeConvert(getEUR as TransferNbt,getRub as TransferNbt,getUsd as TransferNbt)
                    }
                }
            }
            bottomSheet.show(
                requireActivity().supportFragmentManager,
                bottomSheet.tag
            )
        }
    }
    private fun defaultCurrency(rub:TransferNbt) {
        when(changeCurrency){
            0 -> binding.value.text = rub.value
        }
        binding.changeCurrencyResult.setOnCheckedChangeListener { _, ischeck ->
            if(ischeck){
                binding.nameCurrency.text = getText(R.string.name_rub_lg)
                binding.nameCurrencyDown.text = getText(R.string.name_tjk_lg)
                binding.getEditValue.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                    }
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        if(p0!!.isNotEmpty()){
                            var value = p0.toString().toDouble() * rub.value.toDouble()
                            binding.showResult.text = value.toString()
                        }else{
                            binding.showResult.text = resources.getText(R.string.name_sale_num)
                        }
                    }
                    override fun afterTextChanged(p0: Editable?) {
                    }
                })
            }else{
                binding.nameCurrency.text = getText(R.string.name_tjk_lg)
                binding.nameCurrencyDown.text = getText(R.string.name_rub_lg)
                binding.getEditValue.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                    }
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        if(p0!!.isNotEmpty()){
                            var value = p0.toString().toDouble() / rub.value.toDouble()
                            binding.showResult.text = value.toString()
                        }else{
                            binding.showResult.text = resources.getText(R.string.name_sale_num)
                        }
                    }
                    override fun afterTextChanged(p0: Editable?) {
                    }
                })
            }
        }
        binding.getEditValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.isNotEmpty()){
                    binding.showResult.text = (p0.toString().toDouble() * rub.value.toDouble()).toString()
                }else{
                    binding.showResult.text = resources.getText(R.string.name_sale_num)
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
    private fun makeConvert(getEUR: TransferNbt, getRub: TransferNbt, getUsd: TransferNbt) {
        if(changeCurrency == 1){
            binding.value.text = getEUR.value
            binding.getEditValue.text.clear()
            binding.nameCurrency.text = getText(R.string.lg_euro)
            binding.nameCurrencyDown.text = getText(R.string.name_tjk_lg)
            binding.getEditValue.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(p0!!.isNotEmpty()){
                        var value = p0.toString().toDouble() * getEUR.value.toDouble()
                        binding.showResult.text = value.toString()
                    }else{
                        binding.showResult.text = resources.getText(R.string.name_sale_num)
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
            binding.changeCurrencyResult.setOnCheckedChangeListener { _, ischeck ->
                if(ischeck){
                    binding.nameCurrency.text = getText(R.string.lg_euro)
                    binding.nameCurrencyDown.text = getText(R.string.name_tjk_lg)
                    binding.getEditValue.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            if(p0!!.isNotEmpty()){
                                var value = p0.toString().toDouble() * getEUR.value.toDouble()
                                binding.showResult.text = value.toString()
                            }else{
                                binding.showResult.text = resources.getText(R.string.name_sale_num)
                            }
                        }
                        override fun afterTextChanged(p0: Editable?) {
                        }
                    })
                }else{
                    binding.getEditValue.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            if(p0!!.isNotEmpty()){
                                var value = p0.toString().toDouble() / getEUR.value.toDouble()
                                binding.showResult.text =  String.format("%f",value)
                            }else{
                                binding.showResult.text = resources.getText(R.string.name_sale_num)
                            }
                        }
                        override fun afterTextChanged(p0: Editable?) {
                        }
                    })
                    binding.nameCurrency.text = getText(R.string.name_tjk_lg)
                    binding.nameCurrencyDown.text = getText(R.string.lg_euro)
                }
            }
        }else if(changeCurrency == 2){
            binding.value.text = getRub.value
            binding.getEditValue.text.clear()
            binding.nameCurrency.text = getText(R.string.name_rub_lg)
            binding.nameCurrencyDown.text = getText(R.string.name_tjk_lg)
            binding.getEditValue.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(p0!!.isNotEmpty()){
                        var value = p0.toString().toDouble() * getRub.value.toDouble()
                        binding.showResult.text = value.toString()
                    }else{
                        binding.showResult.text = resources.getText(R.string.name_sale_num)
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
            binding.changeCurrencyResult.setOnCheckedChangeListener { _, ischeck ->
                if(ischeck){
                    binding.getEditValue.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            if(p0!!.isNotEmpty()){
                                binding.showResult.text = (p0.toString().toDouble() * getRub.value.toDouble()).toString()
                            }else{
                                binding.showResult.text = resources.getText(R.string.name_sale_num)
                            }
                        }
                        override fun afterTextChanged(p0: Editable?) {
                        }
                    })
                    binding.nameCurrency.text = getText(R.string.name_rub_lg)
                    binding.nameCurrencyDown.text = getText(R.string.name_tjk_lg)
                }else{
                    binding.getEditValue.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            if(p0!!.isNotEmpty()){
                                var value = p0.toString().toDouble() / getRub.value.toDouble()
                                binding.showResult.text =  String.format("%f",value)
                            }else{
                                binding.showResult.text = resources.getText(R.string.name_sale_num)
                            }
                        }
                        override fun afterTextChanged(p0: Editable?) {
                        }
                    })
                    binding.nameCurrency.text = getText(R.string.name_tjk_lg)
                    binding.nameCurrencyDown.text = getText(R.string.name_rub_lg)
                }
            }
        }else if(changeCurrency == 3){
            binding.value.text = getUsd.value
            binding.getEditValue.text.clear()
            binding.nameCurrency.text = getText(R.string.usd_currency_value)
            binding.nameCurrencyDown.text = getText(R.string.name_tjk_lg)
            binding.getEditValue.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(p0!!.isNotEmpty()){
                        binding.showResult.text = (p0.toString().toDouble() * getUsd.value.toDouble()).toString()
                    }else{
                        binding.showResult.text = resources.getText(R.string.name_sale_num)
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
            binding.changeCurrencyResult.setOnCheckedChangeListener { _, ischeck ->
                if(ischeck){
                    binding.getEditValue.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            if(p0!!.isNotEmpty()){
                                var value = p0.toString().toDouble() * getUsd.value.toDouble()
                                binding.showResult.text = value.toString()
                            }else{
                                binding.showResult.text = resources.getText(R.string.name_sale_num)
                            }
                        }
                        override fun afterTextChanged(p0: Editable?) {
                        }
                    })
                    binding.nameCurrency.text = getText(R.string.usd_currency_value)
                    binding.nameCurrencyDown.text = getText(R.string.name_tjk_lg)
                }else{
                    binding.getEditValue.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                        }
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            if(p0!!.isNotEmpty()){
                                var value = p0.toString().toDouble() / getUsd.value.toDouble()
                                binding.showResult.text =  String.format("%f",value)
                            }else{
                                binding.showResult.text = resources.getText(R.string.name_sale_num)
                            }
                        }
                        override fun afterTextChanged(p0: Editable?) {
                        }
                    })
                    binding.nameCurrency.text = getText(R.string.name_tjk_lg)
                    binding.nameCurrencyDown.text = getText(R.string.name_usd)
                }
            }
        }
    }
}