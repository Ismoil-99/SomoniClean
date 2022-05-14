package com.example.somoni.presenetation.uimain.partAdvance

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentAdvancedBinding
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.extensions.ui.showActionBar
import com.example.somoni.presenetation.uisetting.ExampleViewModel

class AdvancedFragment(
) : BaseFragment<ExampleViewModel,FragmentAdvancedBinding>(R.layout.fragment_advanced) {
    override val binding by viewBinding(FragmentAdvancedBinding::bind)
    override val viewModel : ExampleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}