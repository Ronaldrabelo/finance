package com.fincance.my_money.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.fincance.my_money.model.Income
import com.fincance.my_money.model.AppDatabase
import com.fincance.my_money.repository.IncomeRepository

class IncomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: IncomeRepository
    val allIncomes: LiveData<List<Income>>
    val totalIncome: LiveData<Float>

    init {
        val incomeDao = AppDatabase.getDatabase(application).incomeDao()
        repository = IncomeRepository(incomeDao)
        allIncomes = repository.allIncomes
        totalIncome = repository.totalIncome
    }
}
