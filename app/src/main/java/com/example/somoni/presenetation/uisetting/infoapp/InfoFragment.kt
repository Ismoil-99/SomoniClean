package com.example.somoni.presenetation.uisetting.infoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentInfoBinding
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.extensions.navigation.navigateSafely
import com.example.somoni.presenetation.uisetting.ExampleViewModel


class InfoFragment : BaseFragment<ExampleViewModel,FragmentInfoBinding>(R.layout.fragment_info){
    override val viewModel : ExampleViewModel by viewModels()
    override val binding by viewBinding(FragmentInfoBinding::bind)

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tochoosecurrency.setOnClickListener {
            findNavController().navigateSafely(R.id.action_infoAppFragment_to_chooseCurrencyFragment)
        }
    }
}