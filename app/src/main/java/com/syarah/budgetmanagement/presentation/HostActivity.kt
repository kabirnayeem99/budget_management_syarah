package com.syarah.budgetmanagement.presentation

import android.os.Bundle
import androidx.navigation.findNavController
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseActivity
import com.syarah.budgetmanagement.databinding.ActivityHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : BaseActivity<ActivityHostBinding>() {

    override val layout: Int
        get() = R.layout.activity_host


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.nav_host_fragment_content_main)

    }

}