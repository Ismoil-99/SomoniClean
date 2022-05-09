package com.example.somoni.presenetation.uisetting.coursedisplaymode

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentDisplaymodeBinding
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.presenetation.uisetting.ExampleViewModel

class DisplayModeFragment(
) : BaseFragment<ExampleViewModel,FragmentDisplaymodeBinding>(R.layout.fragment_displaymode) {
    override val binding by viewBinding(FragmentDisplaymodeBinding::bind)
    override val viewModel : ExampleViewModel by viewModels()
}