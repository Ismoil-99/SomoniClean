package com.example.somoni.presenetation.uimain.partAdvance.partusually.fragmentusually

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.somoni.R
import com.example.somoni.databinding.FragmentUsuallyBinding

class UsuallyFragment : Fragment(R.layout.fragment_usually) {
    lateinit var binding: FragmentUsuallyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsuallyBinding.inflate(inflater,container,false)
        return binding.root
    }

}