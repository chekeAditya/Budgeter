package com.application.budgeter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.budgeter.repository.MoneyRepo

class MoneyViewModelFactory(val repo: MoneyRepo): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoneyViewModel(repo) as T
    }
}