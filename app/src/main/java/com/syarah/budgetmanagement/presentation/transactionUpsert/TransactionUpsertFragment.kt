package com.syarah.budgetmanagement.presentation.transactionUpsert

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseFragment
import com.syarah.budgetmanagement.databinding.FragmentTransactionUpsertBinding
import com.syarah.budgetmanagement.domain.entity.TransactionCurrency
import com.syarah.budgetmanagement.domain.entity.TransactionType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date

@AndroidEntryPoint
class TransactionUpsertFragment : BaseFragment<FragmentTransactionUpsertBinding>() {
    override val layout: Int
        get() = R.layout.fragment_transaction_upsert

    private val viewModel by activityViewModels<TransactionUpsertViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpData()
    }

    private fun setUpViews() {
        activity?.title = context?.getString(R.string.label_transaction_create_or_update)
        setupMenu()
        binding.apply {
            tvDate.text = Date().toString()
        }
    }

    private val args by navArgs<TransactionUpsertFragmentArgs>()

    private fun setUpData() {
        lifecycleScope.launch {
            viewModel.fetchTransactionDetails(args.transactionId)
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    if (it.transactionDetails != null) {
                        binding.apply {
                            etName.setText(it.transactionDetails.name)
                            etAmount.setText(it.transactionDetails.amount.toString())
                            tbCurrency.check(if (it.transactionDetails.currency == TransactionCurrency.Dinar) R.id.btn_dinar else R.id.btn_dollar)
                            tbType.check(if ((it.transactionDetails.type == TransactionType.Income)) R.id.btn_income else R.id.btn_expense)
                        }
                    }
                }
            }
        }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) = Unit

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_transaction_upsert, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_save -> saveTransaction()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun saveTransaction() {
        lifecycleScope.launch {
            val transactionName = binding.etName.text.toString()
            val amount = binding.etAmount.text.toString().toInt()
            val type =
                if (binding.tbType.checkedButtonId == R.id.btn_expense) TransactionType.Expense else TransactionType.Income
            val currency =
                if (binding.tbCurrency.checkedButtonId == R.id.btn_dinar) TransactionCurrency.Dinar else TransactionCurrency.Dollar

            viewModel.createOrUpdateTransaction(transactionName = transactionName,
                amount = amount,
                type = type,
                currency = currency,
                monthId = args.monthId,
                accountId = args.accountId,
                onSaved = { navController.navigateUp() })
        }
    }
}