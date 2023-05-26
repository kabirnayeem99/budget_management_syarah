package com.syarah.budgetmanagement.presentation.transactionDetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseFragment
import com.syarah.budgetmanagement.databinding.FragmentTransactionDetailsBinding
import com.syarah.budgetmanagement.domain.entity.Transaction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransactionDetailsFragment : BaseFragment<FragmentTransactionDetailsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_transaction_details

    private val viewModel by viewModels<TransactionDetailsViewModel>()
    private val transactionAdapter by lazy { TransactionAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpData()
    }

    private val args: TransactionDetailsFragmentArgs by navArgs()
    private fun setUpData() {
        val accountId = args.accountId
        val monthId = args.monthId
        val yearId = args.year
        viewModel.fetchTransactions(accountId, monthId, yearId)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    transactionAdapter.submitList(state.transactions)
                }
            }
        }
    }

    private fun setUpViews() {
        activity?.title = context?.getString(R.string.label_transaction_details)
        setupMenu()
        binding.apply {
            rvTransactions.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = transactionAdapter
            }
        }
        transactionAdapter.setOnClick { transaction -> navigateToTransactionUpsertScreen(transaction) }
        transactionAdapter.setOnDelete { transaction -> viewModel.deleteTransaction(transaction) }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) = Unit

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_months, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_add -> navigateToTransactionUpsertScreen(null)
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun navigateToTransactionUpsertScreen(transaction: Transaction?) {
        navController.navigate(
            TransactionDetailsFragmentDirections.actionTransactionDetailsFragmentToTransactionUpsertFragment(
                transactionId = transaction?.id ?: -1,
                monthId = transaction?.monthId ?: args.monthId,
                year = transaction?.year ?: args.year,
                accountId = transaction?.accountId ?: args.accountId,
            )
        )
    }
}