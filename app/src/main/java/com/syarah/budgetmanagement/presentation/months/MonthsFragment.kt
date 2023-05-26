package com.syarah.budgetmanagement.presentation.months

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseFragment
import com.syarah.budgetmanagement.databinding.FragmentMonthsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MonthsFragment : BaseFragment<FragmentMonthsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_months

    private val monthAdapter by lazy { MonthAdapter() }
    private val viewModel by activityViewModels<MonthsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpData()
    }

    private fun setUpView() {
        binding.apply {
            rvMonths.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = monthAdapter
            }
            monthAdapter.setOnClick { }
            monthAdapter.setOnEdit { }
            monthAdapter.setOnDelete { month -> viewModel.deleteMonth(month) }
        }
    }

    private fun setUpData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    monthAdapter.submitList(uiState.months)
                }
            }
        }
    }
}