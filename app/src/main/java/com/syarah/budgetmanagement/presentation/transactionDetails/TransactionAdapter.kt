package com.syarah.budgetmanagement.presentation.transactionDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.utility.toArabic
import com.syarah.budgetmanagement.databinding.ListItemTransactionBinding
import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.entity.TransactionCurrency
import com.syarah.budgetmanagement.domain.entity.TransactionType
import java.time.format.DateTimeFormatter
import java.time.format.DecimalStyle
import java.time.format.FormatStyle
import java.util.Locale

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private var _onClick: ((Transaction) -> Unit)? = null
    private var _onDelete: ((Transaction) -> Unit)? = null

    fun submitList(transactions: List<Transaction>) = differ.submitList(transactions)
    fun setOnClick(onClick: ((Transaction) -> Unit)) {
        _onClick = onClick
    }


    fun setOnDelete(onDelete: ((Transaction) -> Unit)) {
        _onDelete = onDelete
    }

    inner class TransactionViewHolder(val binding: ListItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            val context = binding.root.context
            binding.apply {
                val locale: Locale = context.resources.configuration.locales[0]


                tvTransactionDate.text = transaction.date.toLocaleString()
                tvTransactionName.text = transaction.name

                var total = transaction.total.toString()

                if (locale.language == "ar") {
                    total = total.toArabic()
                }

                val money = when (transaction.currency) {
                    TransactionCurrency.Dinar -> context.getString(
                        R.string.dinar_placeholder, total
                    )

                    TransactionCurrency.Dollar -> context.getString(
                        R.string.dollar_placeholder, total
                    )
                }
                tvMoney.text = money
                val moneyColor = when (transaction.type) {
                    TransactionType.Expense -> context.getColor(R.color.md_theme_light_error)
                    TransactionType.Income -> context.getColor(R.color.md_theme_light_primary)
                }
                tvMoney.setTextColor(moneyColor)
                root.setOnClickListener { _onClick?.let { func -> func(transaction) } }
                ivDeleteButton.setOnClickListener { _onDelete?.let { func -> func(transaction) } }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TransactionAdapter.TransactionViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemTransactionBinding.inflate(layoutInflater, parent, false)
        return TransactionViewHolder(binding)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: TransactionAdapter.TransactionViewHolder, position: Int) {
        val transaction = differ.currentList[position]
        holder.bind(transaction)
    }

}