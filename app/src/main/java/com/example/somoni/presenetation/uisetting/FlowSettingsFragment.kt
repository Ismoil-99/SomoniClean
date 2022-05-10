package com.example.somoni.presenetation.uisetting

import com.example.somoni.R
import com.example.somoni.extensions.base.BaseFlowFragment
import com.example.somoni.extensions.ui.hideActionBar

class FlowSettingsFragment : BaseFlowFragment(R.layout.fragment_flow_settings,R.id.navHost_settings){
    override fun onResume() {
        super.onResume()
        hideActionBar()
    }
}