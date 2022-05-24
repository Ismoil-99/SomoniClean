package com.example.somoni.presenetation.uisetting

import com.example.somoni.R
import com.example.somoni.extensions.base.BaseFlowFragment
import com.example.somoni.extensions.ui.hideActionBar
import com.example.somoni.extensions.ui.showActionBar

class FlowSettingsFragment : BaseFlowFragment(R.layout.fragment_flow_settings,R.id.fragment_container_view){
    override fun onResume() {
        super.onResume()
        showActionBar()
    }

    override fun onStop() {
        super.onStop()
        showActionBar()
    }
}