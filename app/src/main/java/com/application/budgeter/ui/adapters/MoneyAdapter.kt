package com.application.budgeter.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.budgeter.R
import com.application.budgeter.local.responses.Money

class MoneyAdapter(
    private val list: List<Money>, private
    val onMoneyItemClicked: OnMoneyItemClicked
) : RecyclerView.Adapter<MoneyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.money_item_view, parent, false)
        return MoneyViewHolder(view, onMoneyItemClicked)
    }

    override fun onBindViewHolder(holder: MoneyViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}