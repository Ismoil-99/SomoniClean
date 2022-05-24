package com.example.somoni.presenetation.uimain

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentMainBinding
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.presenetation.uisetting.ExampleViewModel


class MainFragment : BaseFragment<ExampleViewModel,FragmentMainBinding>(R.layout.fragment_main){
    override val viewModel : ExampleViewModel by viewModels()
    override val binding by viewBinding(FragmentMainBinding::bind)
    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView = binding.bottomNavigationView
        val navController = (childFragmentManager.findFragmentById(R.id.nav_host_menu) as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavigationView,navController)
    }
}