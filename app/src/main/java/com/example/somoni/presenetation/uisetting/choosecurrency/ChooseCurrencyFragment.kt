package com.example.somoni.presenetation.uisetting.choosecurrency

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentChoosecurrencyBinding
import com.example.somoni.extensions.SomoniApp
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.extensions.navigation.activityNavController
import com.example.somoni.extensions.utils.CHANGE_MODE
import com.example.somoni.extensions.utils.CURRENCY_TYPE
import com.example.somoni.extensions.utils.Currency
import com.example.somoni.extensions.utils.SAVE
import com.example.somoni.extensions.navigation.navigateSafely
import com.example.somoni.extensions.navigation.overrideOnBackPressed
import com.example.somoni.extensions.ui.hideActionBar
import com.example.somoni.presenetation.uisetting.ExampleViewModel
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.activity_main.*

class ChooseCurrencyFragment: Fragment(R.layout.fragment_choosecurrency) {
    lateinit var binding: FragmentChoosecurrencyBinding
    private val currencyLiveData = MutableLiveData<Currency>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overrideOnBackPressed { activityNavController().navigateUp() }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChoosecurrencyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        requireActivity()?.toolbar.menu?.findItem(R.id.action_favorite)?.isVisible = false
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currencyLiveData.observe(viewLifecycleOwner) {
            binding.selectCurrency.isEnabled = it != null
        }
        val get = arguments?.getBoolean(CHANGE_MODE, false)
        if (get == true) {
            binding.selectCurrency.text = SAVE
            binding.bottomText.isInvisible = true
            binding.linearLayout.isInvisible = true
        }
        if (SomoniApp.sharedPreferences.contains(CURRENCY_TYPE)) {
            when (SomoniApp.sharedPreferences.getInt(CURRENCY_TYPE, Currency.RUB.currencyId)) {
                Currency.RUB.currencyId -> {
                    checkCurrencyTypeCard(
                        Currency.RUB,
                        binding.cardCurrencyRub,
                        binding.cardCurrencyRub,
                        binding.cardCurrencyUSA,
                        binding.cardCurrencyEuro
                    )
                }
                Currency.USD.currencyId -> {
                    checkCurrencyTypeCard(
                        Currency.USD,
                        binding.cardCurrencyUSA,
                        binding.cardCurrencyRub,
                        binding.cardCurrencyUSA,
                        binding.cardCurrencyEuro
                    )
                }
                Currency.EUR.currencyId -> {
                    checkCurrencyTypeCard(
                        Currency.EUR,
                        binding.cardCurrencyEuro,
                        binding.cardCurrencyRub,
                        binding.cardCurrencyUSA,
                        binding.cardCurrencyEuro
                    )
                }
            }
        }
        //Card RUB
        binding.cardCurrencyRub.setOnClickListener {
            checkCurrencyTypeCard(
                Currency.RUB,
                it as MaterialCardView,
                binding.cardCurrencyRub,
                binding.cardCurrencyUSA,
                binding.cardCurrencyEuro
            )
        }
        //Card USA
        binding.cardCurrencyUSA.setOnClickListener {
            checkCurrencyTypeCard(
                Currency.USD,
                it as MaterialCardView,
                binding.cardCurrencyRub,
                binding.cardCurrencyUSA,
                binding.cardCurrencyEuro
            )
        }
        //Card EURO
        binding.cardCurrencyEuro.setOnClickListener {
            checkCurrencyTypeCard(
                Currency.EUR,
                it as MaterialCardView,
                binding.cardCurrencyRub,
                binding.cardCurrencyUSA,
                binding.cardCurrencyEuro
            )
        }
        //Select card
        binding.selectCurrency.setOnClickListener {
            saveCurrencyType(currencyLiveData.value!!)
            if (get == true) {
                findNavController().navigateUp()
            } else {
                findNavController().navigateSafely(R.id.action_chooseCurrencyFragment_to_displayModeFragment)
            }
        }
    }
    //disabled card
    private fun checkCurrencyTypeCard(
        currency: Currency,
        cardCurrent: MaterialCardView,
        cardRUB: MaterialCardView,
        cardUSD: MaterialCardView,
        cardEUR: MaterialCardView,
    ) {
        fun disableOtherCard(card1: MaterialCardView, card2: MaterialCardView,) {
            card1.isChecked = false
            card1.strokeColor = ContextCompat.getColor(requireContext(), R.color.grey_button)
            card2.isChecked = false
            card2.strokeColor = ContextCompat.getColor(requireContext(), R.color.grey_button)
        }
        cardCurrent.apply {
            isChecked = !isChecked
            if (isChecked) {
                strokeColor = ContextCompat.getColor(requireContext(), R.color.button)
                when (cardCurrent) {
                    cardRUB -> {
                        disableOtherCard(cardEUR, cardUSD)
                    }
                    cardEUR -> {
                        disableOtherCard(cardRUB, cardUSD)
                    }
                    cardUSD -> {
                        disableOtherCard(cardRUB, cardEUR)
                    }
                }
            } else {
                strokeColor =  ContextCompat.getColor(requireContext(), R.color.grey_button)
            }
            currencyLiveData.postValue(
                if (isChecked) currency
                else null
            )
        }
    }
    //save currency option
    private fun saveCurrencyType(currency: Currency) {
        SomoniApp.sharedPreferencesEditor.putInt(CURRENCY_TYPE, currency.currencyId)
        SomoniApp.sharedPreferencesEditor.apply()
    }
}
