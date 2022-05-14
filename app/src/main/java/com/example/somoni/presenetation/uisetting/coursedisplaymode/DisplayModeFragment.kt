package com.example.somoni.presenetation.uisetting.coursedisplaymode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.somoni.R
import com.example.somoni.databinding.FragmentDisplaymodeBinding
import com.example.somoni.extensions.SomoniApp
import com.example.somoni.extensions.navigation.navigateSafely
import com.example.somoni.extensions.utils.CHANGE_MODE
import com.example.somoni.extensions.utils.SAVE
import com.example.somoni.extensions.utils.SELECT_OPTION
import com.example.somoni.extensions.utils.ViewType
import com.google.android.material.card.MaterialCardView

class DisplayModeFragment(
) : Fragment(R.layout.fragment_displaymode) {
    lateinit var binding: FragmentDisplaymodeBinding
    private val currencyLiveData = MutableLiveData<ViewType?>(null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplaymodeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyLiveData.observe(viewLifecycleOwner) {
            binding.optionSave.isEnabled = it != null
        }
        val get = arguments?.getBoolean(CHANGE_MODE)
        if (get == true){
            binding.optionSave.text = SAVE
            binding.bottomText.isInvisible = true
            binding.linearLayout.isInvisible = true
        }
        if (SomoniApp.sharedPreferences.contains(SELECT_OPTION)) {
            when (SomoniApp.sharedPreferences.getInt(SELECT_OPTION, ViewType.SIMPLE.viewTypeId)) {
                ViewType.SIMPLE.viewTypeId -> {
                    modeSelect(
                        ViewType.SIMPLE,
                        binding.cardSimpleMode,
                        binding.cardSimpleMode,
                        binding.cardExtendedMode,
                    )
                }
                ViewType.ADVANCE.viewTypeId -> {
                    modeSelect(
                        ViewType.ADVANCE,
                        binding.cardExtendedMode,
                        binding.cardSimpleMode,
                        binding.cardExtendedMode,
                    )
                }
            }
        }
        binding.cardSimpleMode.setOnClickListener {
            modeSelect(
                ViewType.SIMPLE,
                it as MaterialCardView,
                binding.cardSimpleMode,
                binding.cardExtendedMode
            )
        }
        binding.cardExtendedMode.setOnClickListener {
            modeSelect(
                ViewType.ADVANCE,
                it as MaterialCardView,
                binding.cardSimpleMode,
                binding.cardExtendedMode
            )
        }
        binding.optionSave.setOnClickListener {
            saveMode(currencyLiveData.value!!)
            findNavController().navigateSafely(R.id.action_settings_to_nav_main)
        }
    }
    private fun modeSelect(
        viewType: ViewType,
        cardCurrent: MaterialCardView,
        cardSimple: MaterialCardView,
        cardExtended: MaterialCardView
    ) {
        fun disableCard(card: MaterialCardView) {
            card.isChecked = false
            card.strokeColor = ContextCompat.getColor(requireContext(), R.color.grey_button)
        }
        cardCurrent.apply {
            isChecked = !isChecked
            if (isChecked) {
                strokeColor = ContextCompat.getColor(requireContext(), R.color.button)
                when (cardCurrent) {
                    cardSimple -> {
                        disableCard(cardExtended)
                    }
                    cardExtended -> {
                        disableCard(cardSimple)
                    }
                }
            }else{
                strokeColor = ContextCompat.getColor(requireContext(), R.color.grey_button)
            }
            currencyLiveData.postValue(
                if (isChecked) viewType
                else null
            )
        }
    }
    private fun saveMode(viewType: ViewType) {
        SomoniApp.sharedPreferencesEditor.putInt(SELECT_OPTION, viewType.viewTypeId)
        SomoniApp.sharedPreferencesEditor.apply()
    }
}