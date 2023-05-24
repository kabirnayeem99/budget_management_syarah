package com.syarah.budgetmanagement

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.syarah.budgetmanagement.core.base.BaseActivity
import com.syarah.budgetmanagement.databinding.ActivityHostBinding

class HostActivity : BaseActivity<ActivityHostBinding>() {

    override val layout: Int
        get() = R.layout.activity_host


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.nav_host_fragment_content_main)

    }

}