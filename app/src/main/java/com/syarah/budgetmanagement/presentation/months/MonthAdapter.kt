package com.syarah.budgetmanagement.presentation.months

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.utility.toArabic
import com.syarah.budgetmanagement.databinding.ListItemMonthBinding
import com.syarah.budgetmanagement.domain.entity.Month
import java.util.Locale

class MonthAdapter : RecyclerView.Adapter<MonthAdapter.MonthViewHolder>() {

    private var _onClick: ((Month) -> Unit)? = null
    private var _onEdit: ((Month) -> Unit)? = null
    private var _onDelete: ((Month) -> Unit)? = null

    fun submitList(months: List<Month>) = differ.submitList(months)
    fun setOnClick(onClick: ((Month) -> Unit)) {
        _onClick = onClick
    }

    fun setOnEdit(onEdit: ((Month) -> Unit)) {
        _onEdit = onEdit
    }

    fun setOnDelete(onDelete: ((Month) -> Unit)) {
        _onDelete = onDelete
    }

    inner class MonthViewHolder(val binding: ListItemMonthBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(month: Month) {
            val context = binding.root.context
            binding.apply {

                val locale: Locale = context.resources.configuration.locales[0]
                var dinarIncome = month.dinarIncome.toString()
                var dinarExpense = month.dinarExpense.toString()
                var dollarIncome = month.dollarIncome.toString()
                var dollarExpense = month.dollarExpense.toString()
                var presentableDate = month.presentableDate.toString()

                if (locale.language == "ar") {
                    dinarIncome = dinarIncome.toArabic()
                    dollarIncome = dollarIncome.toArabic()
                    dinarExpense = dinarExpense.toArabic()
                    dollarExpense = dollarExpense.toArabic()
                    presentableDate = presentableDate.toArabic()
                }

                tvMonth.text = presentableDate
                tvCurrencyDinarEarning.text =
                    context.getString(R.string.dinar_placeholder, dinarIncome)
                tvCurrencyDinarSpending.text =
                    context.getString(R.string.dinar_placeholder, dinarExpense)
                tvCurrencyDollarSpending.text =
                    context.getString(R.string.dollar_placeholder, dollarExpense)
                tvCurrencyDollarEarning.text =
                    context.getString(R.string.dollar_placeholder, dollarIncome)

                root.setOnClickListener { _onClick?.let { func -> func(month) } }
                ivDeleteButton.setOnClickListener { _onDelete?.let { func -> func(month) } }
                ivEditButton.setOnClickListener { _onEdit?.let { func -> func(month) } }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMonthBinding.inflate(layoutInflater, parent, false)
        return MonthViewHolder(binding)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Month>() {
        override fun areItemsTheSame(oldItem: Month, newItem: Month): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Month, newItem: Month): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        val month = differ.currentList[position]
        holder.bind(month)
    }
}