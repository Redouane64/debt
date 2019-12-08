package com.github.redouane64.debt.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.redouane64.debt.models.Debt

@Dao
interface Debts {

    @Insert
    fun insert(entity: Debt);

    @Query("SELECT * FROM Debts")
    fun getAll() : List<Debt>

    @Query("SELECT * FROM Debts WHERE dueDate = :date")
    fun getByDate(date: String) : List<Debt>;
}