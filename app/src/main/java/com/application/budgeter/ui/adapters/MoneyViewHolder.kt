package com.application.budgeter.ui.adapters

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.application.budgeter.R
import com.application.budgeter.local.responses.Money
import kotlinx.android.synthetic.main.money_item_view.view.*

class MoneyViewHolder(
    private val itemView: View,
    private val onMoneyItemClicked: OnMoneyItemClicked
) : RecyclerView.ViewHolder(itemView) {

    fun setData(money: Money) {
        itemView.apply {
            tvAmount.text = money.amount.toString()
            if (money.category == "Income")
                tvAmount.setTextColor(ContextCompat.getColor(context, R.color.teal_700))
            else tvAmount.setTextColor(ContextCompat.getColor(context, R.color.red))
            tvDescription.text = money.description
            tvDate.text = money.date
        }
        itemView.item.setOnClickListener {
            onMoneyItemClicked.onClick(money)
        }
    }

}