package com.fincance.my_money.repository

import androidx.lifecycle.LiveData
import com.fincance.my_money.model.Income
import com.fincance.my_money.model.IncomeDao

class IncomeRepository(private val incomeDao: IncomeDao) {

    val allIncomes: LiveData<List<Income>> = incomeDao.getAllIncomes()
    val totalIncome: LiveData<Float> = incomeDao.getTotalIncome()

    suspend fun insertIncome(income: Income) {
        incomeDao.insertIncome(income)
    }

    suspend fun deleteAllIncomes() {
        incomeDao.deleteAllIncomes()
    }
}
