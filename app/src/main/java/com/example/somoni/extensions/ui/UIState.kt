package com.example.somoni.extensions.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.somoni.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

fun Fragment.hideActionBar() = (requireActivity() as AppCompatActivity).supportActionBar?.hide()
fun Fragment.showActionBar() = (requireActivity() as AppCompatActivity).supportActionBar?.show()
fun Fragment.hideArrowBar() = (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)