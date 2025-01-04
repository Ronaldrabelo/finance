package com.fincance.my_money.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IncomeDao {
    @Insert
    suspend fun insertIncome(income: Income)

    @Query("SELECT * FROM income_table")
    suspend fun getAllIncomes(): List<Income>

    @Query("DELETE FROM income_table")
    suspend fun deleteAllIncomes()
}
