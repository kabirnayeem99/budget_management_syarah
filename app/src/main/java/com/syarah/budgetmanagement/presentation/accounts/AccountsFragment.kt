package com.syarah.budgetmanagement.presentation.accounts

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseFragment
import com.syarah.budgetmanagement.databinding.FragmentAccountsBinding

class AccountsFragment : BaseFragment<FragmentAccountsBinding>() {

    override val layout: Int
        get() = R.layout.fragment_accounts

    private val accountAdapter by lazy { AccountAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setData()
    }


    private fun setData() {
        accountAdapter.submitList(emptyList())
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
        accountAdapter.setOnClick {  }
        accountAdapter.setOnDelete {  }
        accountAdapter.setOnEdit {  }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_accounts, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Validate and handle the selected menu item
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}