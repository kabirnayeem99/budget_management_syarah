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
import androidx.recyclerview.widget.LinearLayoutManager
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseFragment
import com.syarah.budgetmanagement.databinding.FragmentTransactionDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionDetailsFragment : BaseFragment<FragmentTransactionDetailsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_transaction_details

    private val viewModel by viewModels<TransactionDetailsViewModel>()
    private val transactionAdapter by lazy { TransactionAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setupMenu()
    }

    private fun setUpViews() {
        activity?.title = context?.getString(R.string.label_accounts)
        setupMenu()
        binding.apply {
            rvTransactions.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = transactionAdapter
            }
        }
//        transactionAdapter.setOnClick { transaction -> navigateToMonthScreen(account) }
        transactionAdapter.setOnDelete { transaction -> viewModel.deleteTransaction(transaction) }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) = Unit

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_accounts, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
//                    R.id.menu_add -> showAdEditAccountDialog(null)
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}