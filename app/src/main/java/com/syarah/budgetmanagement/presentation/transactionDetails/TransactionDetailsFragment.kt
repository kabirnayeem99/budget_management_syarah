package com.syarah.budgetmanagement.presentation.transactionDetails

import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseFragment
import com.syarah.budgetmanagement.databinding.FragmentTransactionDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionDetailsFragment : BaseFragment<FragmentTransactionDetailsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_transaction_details
}