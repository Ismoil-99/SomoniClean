package com.example.somoni.presenetation.uimain.partAdvance.changemode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.somoni.R
import com.example.somoni.databinding.FragmentChangeBinding
import com.example.somoni.extensions.SomoniApp
import com.example.somoni.extensions.navigation.navigateSafely
import com.example.somoni.extensions.ui.showActionBar
import com.example.somoni.extensions.utils.*

class ChangeFragment:Fragment() {
    lateinit var binding: FragmentChangeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.changeModeCurrency.setOnClickListener {
            val changeModeCurrency = SomoniApp.sharedPreferencesEditor.putBoolean("CHANGEMODCURRENCY",true)
            changeModeCurrency.commit()
            findNavController().navigateSafely(R.id.action_changeFragment3_to_displayModeFragment2)
        }
        binding.changeCurrencyDefault.setOnClickListener {
            val changeCurrency = SomoniApp.sharedPreferencesEditor.putBoolean("CHANGECURRENCY",true)
            changeCurrency.commit()
            findNavController().navigateSafely(R.id.action_changeFragment3_to_chooseCurrencyFragment2)
        }
    }

    override fun onResume() {
        super.onResume()
        showActionBar()
        val changeMode = SomoniApp.sharedPreferences.getInt(SELECT_OPTION, -1)
        optionMode(changeMode)
        val changeOptionCurrency = SomoniApp.sharedPreferences.getInt(CURRENCY_TYPE, -1)
        currencyOption(changeOptionCurrency)
    }

    private fun optionMode(changeMode: Int) {
        when (changeMode) {
            ViewType.SIMPLE.viewTypeId -> binding.modeText.text =
                resources.getString(R.string.name_rezh)
            ViewType.ADVANCE.viewTypeId -> binding.modeText.text =
                resources.getString(R.string.name_rezh_all)
        }
    }

    private fun currencyOption(changeOptionCurrency: Int) {
        when (changeOptionCurrency) {
            Currency.RUB.currencyId -> binding.currencyText.text =
                resources.getString(R.string.lg_russian)
            Currency.EUR.currencyId -> binding.currencyText.text =
                resources.getString(R.string.lg_euro)
            Currency.USD.currencyId -> binding.currencyText.text =
                resources.getString(R.string.lg_usa)
        }
    }
}