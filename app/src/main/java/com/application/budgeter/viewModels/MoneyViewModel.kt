package com.application.budgeter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.budgeter.local.responses.Money
import com.application.budgeter.repository.MoneyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoneyViewModel @Inject constructor(private val repo: MoneyRepo): ViewModel() {

    fun addMoney(money: Money){
        repo.addMoneyToRoom(money)
    }

    fun updateMoney(money: Money){
        repo.updateMoney(money)
    }

    fun deleteMoney(money: Money){
        repo.deleteMoney(money)
    }

    fun getAllMoney(category: String): LiveData<List<Money>>{
        return repo.getAllMoney(category)
    }

}