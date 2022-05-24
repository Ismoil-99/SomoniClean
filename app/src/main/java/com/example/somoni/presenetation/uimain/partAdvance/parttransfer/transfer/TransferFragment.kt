package com.example.somoni.presenetation.uimain.partAdvance.parttransfer.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.somoni.R
import com.example.somoni.databinding.FragmentTransferBinding

class TransferFragment : Fragment(R.layout.fragment_transfer) {
    lateinit var binding: FragmentTransferBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransferBinding.inflate(inflater,container,false)
        return binding.root
    }
}