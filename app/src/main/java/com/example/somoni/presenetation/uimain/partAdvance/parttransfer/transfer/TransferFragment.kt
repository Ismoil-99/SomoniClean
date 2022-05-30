package com.example.somoni.presenetation.uimain.partAdvance.parttransfer.transfer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.somoni.R
import com.example.somoni.databinding.FragmentTransferBinding
import com.example.somoni.extensions.SomoniApp
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.extensions.utils.*
import com.example.somoni.presenetation.uimain.partAdvance.parttransfer.adaptertransfer.AdapterTransfer
import com.example.somoni.presenetation.uimain.partAdvance.parttransfer.bottomsheetstransfer.BottomSheetsTransfer
import com.example.somoni.presenetation.uimain.partAdvance.parttransfer.transferviewmodel.TransferViewModel
import com.example.somoni.presenetation.uimain.partAdvance.partusually.bottomsheetsusually.BottomSheetsUsually
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransferFragment : BaseFragment<TransferViewModel,FragmentTransferBinding>(R.layout.fragment_transfer) {
    override val binding by viewBinding(FragmentTransferBinding::bind)
    override val viewModel : TransferViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    var gettransferRus: AdapterTransfer? = null
    var getMode = SomoniApp.sharedPreferences.getInt(SELECT_OPTION, 0)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.getTransferRusCurrency
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            gettransferRus = AdapterTransfer{ currency, currencyId ->
                val bundle = Bundle()
                bundle.putString(NAME_COLOR_ONE,currency.colors.color_1)
                bundle.putString(NAME_COLOR_SECOND,currency.colors.color_2)
                bundle.putSerializable(CurrencyINFO,currency.Currency)
                bundle.putString(NAME_CURRENCY_VALUE,currency.bank_name)
                bundle.putString(NAME_ICON,currency.icon)
                bundle.putInt(NAME_POSITION,currencyId)
                val bottomSheet = BottomSheetsTransfer()
                bottomSheet.arguments = bundle
                bottomSheet.show(
                    requireActivity().supportFragmentManager,
                    bottomSheet.tag
                )
            }
            adapter = gettransferRus
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTransferRus()
        binding.invisibleSale.isVisible = getMode != 0
        showData()
    }

    @SuppressLint("RepeatOnLifecycleWrongUsage")
    private fun showData() {
        getMode = SomoniApp.sharedPreferences.getInt(SELECT_OPTION, 0)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.transferRus.collect {
                    when (it.state) {
                        State.ERROR -> {
                            binding.getColor.visibility = View.GONE
                            binding.getTransferRusCurrency.visibility = View.GONE
                            binding.error.visibility = View.VISIBLE
                            binding.loading.visibility = View.GONE
                        }
                        State.SUCCESS -> {
                            binding.error.visibility = View.GONE
                            binding.loading.visibility = View.GONE
                            binding.getColor.visibility = View.VISIBLE
                            binding.getTransferRusCurrency.visibility = View.VISIBLE
                            val dataRus = it.data!!
                            gettransferRus?.get(dataRus)
                            binding.nameIcon.load(dataRus[0].icon){
                                crossfade(true)
                                placeholder(R.drawable.ic_refresh)
                            }
                            binding.currencyNameHumo .text = dataRus[0].Currency[0].buy_value
                            binding.nameCurrencySell.text = dataRus[0].Currency[1].sell_value
                        }
                        State.LOADING -> {
                            binding.loading.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}