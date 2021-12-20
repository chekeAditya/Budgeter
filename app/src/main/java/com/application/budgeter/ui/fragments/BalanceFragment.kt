package com.application.budgeter.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.application.budgeter.R
import com.application.budgeter.local.responses.Money
import com.application.budgeter.viewModels.MoneyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_balance.*
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class BalanceFragment : Fragment(R.layout.fragment_balance) {

    private var cal = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var datePickerStart: DatePickerDialog.OnDateSetListener
    private lateinit var datePickerEnd: DatePickerDialog.OnDateSetListener

    private var listIncome = mutableListOf<Money>()
    private var listExpense = mutableListOf<Money>()

    private val moneyViewModel: MoneyViewModel by viewModels()

    private var totalIncome = 0F
    private var totalExpense = 0F

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchDataFromDB()
        startDateClicked()
        endDatePickedClicked()
        datePickerDialog()
        goButtonClicked()
    }

    private fun goButtonClicked() {
        btnGo.setOnClickListener {
            cvOverAllBalance.visibility = View.VISIBLE
            totalIncome = 0F
            totalExpense = 0F
            val dateStart = dateFormat.parse(tvStartDate.text.toString())
            val dateEnd = dateFormat.parse(tvEndDate.text.toString())
            for (i in listIncome) {
                val dateCheck = dateFormat.parse(i.date)
                if (dateCheck >= dateStart && dateCheck <= dateEnd)
                    totalIncome += i.amount
            }
            for (i in listExpense) {
                val dateCheck = dateFormat.parse(i.date)
                if (dateCheck >= dateStart && dateCheck <= dateEnd)
                    totalExpense += i.amount
            }
            tvIncomeC.text = "Income: $totalIncome"
            tvExpenseC.text = "Expense: $totalExpense"
            tvTotalC.text = "Total: ${totalIncome - totalExpense}"
        }
    }

    private fun endDatePickedClicked() {
        tvEndDate.setOnClickListener {
            val mYear = cal.get(Calendar.YEAR)
            val mMonth = cal.get(Calendar.MONTH)
            val mDay = cal.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(requireContext(), datePickerEnd, mYear, mMonth, mDay).show()
        }
    }

    private fun startDateClicked() {
        tvStartDate.setOnClickListener {
            val mYear = cal.get(Calendar.YEAR)
            val mMonth = cal.get(Calendar.MONTH)
            val mDay = cal.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(requireContext(), datePickerStart, mYear, mMonth, mDay).show()
        }
    }

    private fun fetchDataFromDB() {
        tvStartDate.text = dateFormat.format(cal.time).toString()
        tvEndDate.text = dateFormat.format(cal.time).toString()

        moneyViewModel.getAllMoney("Income").observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {
                listIncome.clear()
                listIncome.addAll(it)
                updateTotalBalance()
            })

        moneyViewModel.getAllMoney("Expense").observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {
                listExpense.clear()
                listExpense.addAll(it)
                updateTotalBalance()
            })

    }

    private fun updateTotalBalance() {
        totalIncome = 0F
        totalExpense = 0F
        for (i in listIncome)
            totalIncome += i.amount
        for (i in listExpense)
            totalExpense += i.amount

        tvIncome.text = "Income:     $totalIncome"
        tvExpense.text = "Expense:   $totalExpense"
        tvTotal.text = "Total:         ${totalIncome - totalExpense}"
    }

    private fun datePickerDialog() {
        datePickerStart = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            tvStartDate.text = dateFormat.format(cal.time).toString()
        }
        datePickerEnd = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            tvEndDate.text = dateFormat.format(cal.time).toString()
        }
    }
}