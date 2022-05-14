package com.example.somoni.presenetation.uisetting.infoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentInfoBinding
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.extensions.navigation.navigateSafely
import com.example.somoni.presenetation.uisetting.ExampleViewModel


class InfoFragment : Fragment(R.layout.fragment_info){
    lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tochoosecurrency.setOnClickListener {
            findNavController().navigateSafely(R.id.action_infoAppFragment_to_chooseCurrencyFragment)
        }
    }
}