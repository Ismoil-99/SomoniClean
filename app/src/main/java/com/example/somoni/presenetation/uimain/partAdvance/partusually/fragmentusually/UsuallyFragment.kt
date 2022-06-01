package com.example.somoni.presenetation.uimain.partAdvance.partusually.fragmentusually

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
import com.example.somoni.databinding.FragmentUsuallyBinding
import com.example.somoni.extensions.SomoniApp
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.extensions.utils.*
import com.example.somoni.presenetation.uimain.partAdvance.partusually.bottomsheetsusually.BottomSheetsUsually
import com.example.somoni.presenetation.uimain.partAdvance.partusually.usuallyadapter.UsuallyAdapter
import com.example.somoni.presenetation.uimain.partAdvance.partusually.viewmodelusually.UsuallyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsuallyFragment : BaseFragment<UsuallyViewModel,FragmentUsuallyBinding>(R.layout.fragment_usually) {
    override val binding by viewBinding(FragmentUsuallyBinding::bind)
    override val viewModel : UsuallyViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    var getCurrency: UsuallyAdapter? = null
    var getMode = SomoniApp.sharedPreferences.getInt(SELECT_OPTION, 0)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.getUsualCurrency
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            getCurrency = UsuallyAdapter{ currency, currencyId ->
                val bundle = Bundle()
                bundle.putString(NAME_COLOR_ONE, currency.colors.color_1)
                bundle.putString(NAME_COLOR_SECOND, currency.colors.color_2)
                bundle.putSerializable(CurrencyINFO, currency.Currency)
                bundle.putString(NAME_ICON, currency.icon)
                bundle.putString(NAME_CURRENCY_VALUE, currency.bank_name)
                val bottomSheet = BottomSheetsUsually()
                bottomSheet.arguments = bundle
                bottomSheet.show(
                    requireActivity().supportFragmentManager,
                    bottomSheet.tag
                )
            }
            adapter = getCurrency
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.regularTransfer()
        getMode = SomoniApp.sharedPreferences.getInt(SELECT_OPTION, 0)
        binding.visible.isVisible = getMode != 0
        showValueRealTime()
    }


    @SuppressLint("RepeatOnLifecycleWrongUsage")
    private fun showValueRealTime() {
        var getCurrencyType = SomoniApp.sharedPreferences.getInt(CURRENCY_TYPE, 0)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.regular.collect {
                    when (it.state) {
                        State.ERROR -> {
                            binding.loading.visibility = View.GONE
                            binding.getColor.visibility = View.GONE
                            binding.getUsualCurrency.visibility = View.GONE
                            binding.error.visibility = View.VISIBLE
                        }
                        State.SUCCESS -> {
                            binding.loading.visibility = View.GONE
                            binding.error.visibility = View.GONE
                            binding.getColor.visibility = View.VISIBLE
                            binding.getUsualCurrency.visibility = View.VISIBLE
                            val data = it.data!!
                            getCurrency?.get(data)
                            binding.nameIcon.load(data[GET_CURRENCY_DATA_TWO].icon) {
                                crossfade(true)
                                placeholder(R.drawable.ic_refresh)
                            }
                            when (getCurrencyType) {
                                GET_CURRENCY_DATA_ZERO -> {
                                    binding.cardCurrency.text = context?.getText(R.string.name_rub)
                                    binding.currencyNameHumo.text =
                                        data[DEFAULT_NUMBER].Currency[GET_CURRENCY_DATA_ONE].buy_value
                                    binding.nameCurrencySell.text =
                                        data[DEFAULT_NUMBER].Currency[GET_CURRENCY_DATA_ONE].sell_value
                                }
                                GET_CURRENCY_DATA_ONE -> {
                                    binding.cardCurrency.text = context?.getText(R.string.name_usd)
                                    binding.currencyNameHumo.text =
                                        data[DEFAULT_NUMBER].Currency[GET_CURRENCY_DATA_ZERO].buy_value
                                    binding.nameCurrencySell.text =
                                        data[DEFAULT_NUMBER].Currency[GET_CURRENCY_DATA_ZERO].sell_value
                                }
                                GET_CURRENCY_DATA_TWO -> {
                                    binding.cardCurrency.text = context?.getText(R.string.name_euro)
                                    binding.currencyNameHumo.text =
                                        data[DEFAULT_NUMBER].Currency[GET_CURRENCY_DATA_TWO].buy_value
                                    binding.nameCurrencySell.text =
                                        data[DEFAULT_NUMBER].Currency[GET_CURRENCY_DATA_TWO].sell_value
                                }
                            }
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