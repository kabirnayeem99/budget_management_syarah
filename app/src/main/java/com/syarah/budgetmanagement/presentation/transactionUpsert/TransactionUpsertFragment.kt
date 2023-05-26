package com.syarah.budgetmanagement.presentation.transactionUpsert

import androidx.fragment.app.activityViewModels
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseFragment
import com.syarah.budgetmanagement.databinding.FragmentTransactionUpsertBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionUpsertFragment : BaseFragment<FragmentTransactionUpsertBinding>() {
    override val layout: Int
        get() = R.layout.fragment_transaction_upsert

    private val viewModel by activityViewModels<TransactionUpsertViewModel>()
}