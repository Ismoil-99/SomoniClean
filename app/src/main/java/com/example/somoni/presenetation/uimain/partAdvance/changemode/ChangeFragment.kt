package com.example.somoni.presenetation.uimain.partAdvance.changemode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.somoni.R
import com.example.somoni.databinding.FragmentChangeBinding
import com.example.somoni.extensions.navigation.activityNavController
import com.example.somoni.extensions.navigation.navigateSafely
import com.example.somoni.extensions.navigation.overrideOnBackPressed
import com.example.somoni.extensions.ui.hideBottom
import com.example.somoni.extensions.ui.showActionBar

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
            findNavController().navigateSafely(R.id.action_changeFragment3_to_displayModeFragment2)
        }
        binding.changeCurrencyDefault.setOnClickListener {
            findNavController().navigateSafely(R.id.action_changeFragment3_to_chooseCurrencyFragment2)
        }
    }

    override fun onResume() {
        super.onResume()
        showActionBar()
    }
}