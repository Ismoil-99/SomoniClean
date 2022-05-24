package com.example.somoni.presenetation.uimain.partAdvance.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.somoni.presenetation.uimain.partAdvance.parttransfer.transfer.TransferFragment
import com.example.somoni.presenetation.uimain.partAdvance.partusually.fragmentusually.UsuallyFragment


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return UsuallyFragment()
            1 -> return TransferFragment()
        }
        return UsuallyFragment()
    }
}