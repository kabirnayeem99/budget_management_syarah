package com.syarah.budgetmanagement.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.documentfile.provider.DocumentFile
import androidx.navigation.findNavController
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseActivity
import com.syarah.budgetmanagement.databinding.ActivityHostBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.FileOutputStream

@AndroidEntryPoint
class HostActivity : BaseActivity<ActivityHostBinding>() {

    override val layout: Int
        get() = R.layout.activity_host


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findNavController(R.id.nav_host_fragment_content_main)

    }






}