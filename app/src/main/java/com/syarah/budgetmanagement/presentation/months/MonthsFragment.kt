package com.syarah.budgetmanagement.presentation.months

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.base.BaseFragment
import com.syarah.budgetmanagement.databinding.FragmentMonthsBinding
import com.syarah.budgetmanagement.domain.entity.Month
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Calendar
import java.util.Date

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
        activity?.title = context?.getString(R.string.label_months)
        setupMenu()
        binding.apply {
            rvMonths.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = monthAdapter
            }
            monthAdapter.setOnClick { month -> navigateToTransactionDetailsScreen(month) }
            monthAdapter.setOnEdit { month -> showMonthSelectionDialog(month) }
            monthAdapter.setOnDelete { month -> viewModel.deleteMonth(month) }
        }
    }

    private fun navigateToTransactionDetailsScreen(month: Month) {
        val direction = MonthsFragmentDirections.actionMonthsFragmentToTransactionDetailsFragment(
            monthId = month.monthId, year = month.year, accountId = args.accountId
        )
        navController.navigate(direction)
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) = Unit

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_months, menu)
            }


            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_add -> showMonthSelectionDialog(null)
                }
                return true
            }


        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showMonthSelectionDialog(month: Month?) {

        val defaultSelection = getSelectionTimeFromMonth(month)
        viewModel.setEditingMonth(month)

        val dialog = MaterialDatePicker.Builder.datePicker().setSelection(defaultSelection)
            .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
            .setTitleText(requireContext().getString(R.string.hint_select_month)).build()
        dialog.addOnPositiveButtonClickListener { timeInMilliseconds ->
            val calendar = Calendar.getInstance()
            calendar.time = Date(timeInMilliseconds)
            val selectedMonthId = calendar.get(Calendar.MONTH) + 1
            val selectedYear = calendar.get(Calendar.YEAR)
            viewModel.addOrUpdateMonth(month = selectedMonthId, year = selectedYear)
        }
        dialog.show(childFragmentManager, "DATE_DIALOG")
    }

    private fun getSelectionTimeFromMonth(month: Month?): Long {
        val selectionCalendar = Calendar.getInstance()
        month?.let { m ->
            selectionCalendar.set(Calendar.MONTH, m.monthId - 1)
            selectionCalendar.set(Calendar.YEAR, m.year)
        }
        return selectionCalendar.time.time
    }

    private val args: MonthsFragmentArgs by navArgs()

    private fun setUpData() {
        lifecycleScope.launch {
            val accountId = args.accountId
            viewModel.fetchMonths(accountId)
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    monthAdapter.submitList(uiState.months)
                }
            }
        }
    }
}