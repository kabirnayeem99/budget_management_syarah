package com.syarah.budgetmanagement.presentation.months

import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseFragment
import com.syarah.budgetmanagement.databinding.FragmentMonthsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MonthsFragment : BaseFragment<FragmentMonthsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_months
}