package com.application.budgeter.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.budgeter.R
import com.application.budgeter.local.responses.Money
import com.application.budgeter.ui.activities.AddEditItemActivity
import com.application.budgeter.viewModels.MoneyViewModel
import com.application.budgeter.ui.adapters.MoneyAdapter
import com.application.budgeter.ui.adapters.OnMoneyItemClicked
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_income.*

@AndroidEntryPoint
class IncomeFragment : Fragment(R.layout.fragment_income), OnMoneyItemClicked {

    private var listMoney = mutableListOf<Money>()

    private val moneyViewModel: MoneyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewIncome.adapter = MoneyAdapter(listMoney, this)
        recyclerViewIncome.layoutManager = LinearLayoutManager(context)
        getDataFromDB()
    }

    private fun getDataFromDB() {
        moneyViewModel.getAllMoney("Income").observe(viewLifecycleOwner, Observer {
            listMoney.clear()
            listMoney.addAll(it)
            recyclerViewIncome.adapter?.notifyDataSetChanged()
        })
    }

    override fun onClick(money: Money) {
        val alertDialog = AlertDialog.Builder(context).create()
        val customLayout = layoutInflater.inflate(R.layout.alert_dialog_view, null)
        alertDialog.setView(customLayout)
        alertDialog.show()
        val mBtnEdit = customLayout.findViewById<Button>(R.id.btnEdit)
        val mBtnDelete = customLayout.findViewById<Button>(R.id.btnDelete)
        mBtnEdit.setOnClickListener {
            val intent = Intent(context, AddEditItemActivity::class.java)
            intent.putExtra("category", money)
            startActivity(intent)
        }

        mBtnDelete.setOnClickListener {
            moneyViewModel.deleteMoney(money)
            alertDialog.dismiss()
        }
    }

}