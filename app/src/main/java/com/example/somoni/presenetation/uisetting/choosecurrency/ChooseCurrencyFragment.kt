package com.example.somoni.presenetation.uisetting.choosecurrency

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentChoosecurrencyBinding
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.extensions.navigation.navigateSafely
import com.example.somoni.presenetation.uisetting.ExampleViewModel

class ChooseCurrencyFragment(
) : BaseFragment<ExampleViewModel,FragmentChoosecurrencyBinding>(R.layout.fragment_choosecurrency) {
    override val binding by viewBinding(FragmentChoosecurrencyBinding::bind)
    override val viewModel : ExampleViewModel by viewModels()
    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.todisplaymode.setOnClickListener {
            findNavController().navigateSafely(R.id.action_chooseCurrencyFragment_to_displayModeFragment)
        }
    }
}