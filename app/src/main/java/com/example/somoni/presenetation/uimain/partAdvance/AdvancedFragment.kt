package com.example.somoni.presenetation.uimain.partAdvance

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somoni.R
import com.example.somoni.databinding.FragmentAdvancedBinding
import com.example.somoni.extensions.base.BaseFragment
import com.example.somoni.extensions.ui.hideArrowBar
import com.example.somoni.extensions.ui.showActionBar
import com.example.somoni.presenetation.uimain.partAdvance.viewpager.ViewPagerAdapter
import com.example.somoni.presenetation.uisetting.ExampleViewModel
import com.google.android.material.tabs.TabLayoutMediator

class AdvancedFragment
 : BaseFragment<ExampleViewModel,FragmentAdvancedBinding>(R.layout.fragment_advanced) {
    override val binding by viewBinding(FragmentAdvancedBinding::bind)
    override val viewModel : ExampleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter =
            ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text =
                if (position == 0) resources.getString(R.string.usual) else resources.getString(R.string.transfer_rf)
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        showActionBar()
        hideArrowBar()
    }
}