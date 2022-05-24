package com.example.somoni.presenetation.uimain.partNbt.nbtfragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentNbtBinding
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.extensions.ui.hideActionBar
import com.example.somoni.extensions.ui.hideBottom
import com.example.somoni.presenetation.uimain.partNbt.Nbtviewmodel.NbtViewModel

class NbtFragment: BaseFragment<NbtViewModel,FragmentNbtBinding>(R.layout.fragment_nbt) {
    override val binding by viewBinding(FragmentNbtBinding::bind)
    override val viewModel : NbtViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
    }
}