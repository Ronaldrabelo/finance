package com.fincance.my_money.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income_table")
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: Float,
    val date: String,
    val description: String,
    val repeatTimes: Int? = null
)
