package com.example.somoni.presenetation.uimain.partNbt.nbtfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentNbtBinding
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.extensions.ui.hideActionBar
import com.example.somoni.extensions.utils.CURRENCY_EUR
import com.example.somoni.extensions.utils.CURRENCY_RUS
import com.example.somoni.extensions.utils.CURRENCY_USD
import com.example.somoni.extensions.utils.State
import com.example.somoni.presenetation.uimain.partNbt.nbtviewmodel.NbtViewModel
import com.example.somoni.presenetation.uimain.partNbt.adapternbt.AdapterNbtCurrency
import com.example.somoni.presenetation.uimain.partNbt.bottomsheet.BottomSheetsNBTTransfer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NbtFragment: BaseFragment<NbtViewModel,FragmentNbtBinding>(R.layout.fragment_nbt) {
    override val binding by viewBinding(FragmentNbtBinding::bind)
    override val viewModel : NbtViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    lateinit var getNbtCurrency: AdapterNbtCurrency
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.nbtCurrency
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            getNbtCurrency = AdapterNbtCurrency()
            adapter = getNbtCurrency
        }
        showValue()

    }
    private fun showValue() {
        val bundle = Bundle()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.transferNbt.collectLatest {
                    when(it.state){
                        State.ERROR -> {
                            binding.loading.visibility = View.GONE
                            binding.nbtCurrency.visibility = View.GONE
                            binding.error.visibility = View.VISIBLE
                        }
                        State.SUCCESS -> {
                            binding.loading.visibility = View.GONE
                            binding.error.visibility = View.GONE
                            getNbtCurrency.getCurrencyNbt(it.dataNbts!!)
                            bundle?.putSerializable(CURRENCY_USD,it.dataNbts[0])
                            bundle?.putSerializable(CURRENCY_EUR,it.dataNbts[1])
                            bundle?.putSerializable(CURRENCY_RUS,it.dataNbts[4])
                            binding.convertationCurrency.setOnClickListener {
                                val bottomSheet = BottomSheetsNBTTransfer()
                                bottomSheet?.arguments = bundle
                                bottomSheet.show(
                                    requireActivity().supportFragmentManager,
                                    bottomSheet.tag
                                )
                            }
                        }
                        State.LOADING -> {
                            binding.nbtCurrency.visibility = View.GONE
                            binding.loading.visibility = View.VISIBLE
                            binding.error.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.getNbt()
    }
}