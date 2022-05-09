package com.example.somoni.presenetation.uimain

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentExampleBinding
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.presenetation.uisetting.ExampleViewModel


class ExampleFragment : BaseFragment<ExampleViewModel,FragmentExampleBinding>(R.layout.fragment_example){
    override val viewModel : ExampleViewModel by viewModels()
    override val binding by viewBinding(FragmentExampleBinding::bind)

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}