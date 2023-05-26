package com.syarah.budgetmanagement.presentation.accounts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.syarah.budgetmanagement.R
import com.syarah.budgetmanagement.core.utility.toArabic
import com.syarah.budgetmanagement.databinding.ListItemAccountBinding
import com.syarah.budgetmanagement.domain.entity.Account
import java.util.Locale

class AccountAdapter : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    private var _onClick: ((Account) -> Unit)? = null
    private var _onEdit: ((Account) -> Unit)? = null
    private var _onDelete: ((Account) -> Unit)? = null

    fun submitList(accounts: List<Account>) = differ.submitList(accounts)
    fun setOnClick(onClick: ((Account) -> Unit)) {
        _onClick = onClick
    }

    fun setOnEdit(onEdit: ((Account) -> Unit)) {
        _onEdit = onEdit
    }

    fun setOnDelete(onDelete: ((Account) -> Unit)) {
        _onDelete = onDelete
    }

    inner class AccountViewHolder(private val binding: ListItemAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) {
            val context = binding.root.context
            binding.apply {

                val locale: Locale = context.resources.configuration.locales[0]
                var dinarAmount = account.dinarAmount.toString()
                var dollarAmount = account.dollarAmount.toString()

                if (locale.language == "ar") {
                    dinarAmount = dinarAmount.toArabic()
                    dollarAmount = dollarAmount.toArabic()
                }

                tvAccountName.text = account.name
                tvCurrencyDinar.text = context.getString(R.string.dinar_placeholder, dinarAmount)
                tvCurrencyDollar.text =
                    context.getString(R.string.dollar_placeholder, dollarAmount)


                root.setOnClickListener { _onClick?.let { func -> func(account) } }
                ivDeleteButton.setOnClickListener { _onDelete?.let { func -> func(account) } }
                ivEditButton.setOnClickListener { _onEdit?.let { func -> func(account) } }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemAccountBinding.inflate(layoutInflater, parent, false)
        return AccountViewHolder(binding)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Account>() {
        override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = differ.currentList[position]
        holder.bind(account)
    }


}