package com.example.somoni.presenetation.screensplash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.somoni.R
import com.example.somoni.databinding.FragmentScreenLayoutBinding
import com.example.somoni.extensions.navigation.navigateSafely
import com.example.somoni.extensions.ui.hideActionBar

class SplashScreenFragment :Fragment(R.layout.fragment_screen_layout){
    lateinit var binding:FragmentScreenLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreenLayoutBinding.inflate(inflater,container,false)
        hideActionBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed({
            findNavController().navigateSafely(R.id.splash_screenFragment_to_nav_settings)
        },2000)
    }

}