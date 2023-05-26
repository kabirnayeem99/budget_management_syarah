package com.syarah.budgetmanagement.presentation.accounts

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseFragment
import com.syarah.budgetmanagement.databinding.FragmentAccountsBinding
import com.syarah.budgetmanagement.domain.entity.Account
import com.syarah.budgetmanagement.presentation.months.MonthsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountsFragment : BaseFragment<FragmentAccountsBinding>() {

    override val layout: Int
        get() = R.layout.fragment_accounts

    private val accountAdapter by lazy { AccountAdapter() }
    private val viewModel by activityViewModels<AccountsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setData()
    }


    private fun setData() {
        lifecycleScope.launch {
            viewModel.fetchAccounts()
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    accountAdapter.submitList(uiState.accounts)
                }
            }
        }
    }

    private fun setUpViews() {
        activity?.title = context?.getString(R.string.label_accounts)
        setupMenu()
        binding.apply {
            rvAccounts.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = accountAdapter
            }
        }
        accountAdapter.setOnClick { account -> navigateToMonthScreen(account) }
        accountAdapter.setOnDelete { account -> viewModel.deleteAccount(account) }
        accountAdapter.setOnEdit { account -> showAdEditAccountDialog(account) }
    }

    private fun navigateToMonthScreen(account: Account) {
        val direction =
            AccountsFragmentDirections.actionAccountsFragmentToMonthsFragment(accountId = account.id)
        navController.navigate(direction)
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) = Unit

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_accounts, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_add -> showAdEditAccountDialog(null)
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showAdEditAccountDialog(account: Account?) {
        viewModel.setEditableAccount(account)
        AddAccountDialogFragment().show(childFragmentManager, "AddAccountDialogFragment")
    }

}