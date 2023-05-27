package com.syarah.budgetmanagement.presentation.accounts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseDialogFragment
import com.syarah.budgetmanagement.databinding.FragmentDialogAddAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class AddAccountDialogFragment : BaseDialogFragment<FragmentDialogAddAccountBinding>() {
    override val layout: Int
        get() = R.layout.fragment_dialog_add_account

    private val viewModel: AccountsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpData()
    }

    private fun setUpViews() {
        binding.apply {
            btnSave.setOnClickListener {
                val accountName = etName.text.toString()
                viewModel.createOrUpdateAccount(accountName, onError = { errorMessage ->
                    tilName.error = errorMessage
                }, onFinished = { dismiss() })
            }
        }
    }

    private fun setUpData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    val editableAccount = uiState.editableAccount
                    editableAccount?.let { account -> binding.etName.setText(account.name) }
                }
            }
        }
    }

}