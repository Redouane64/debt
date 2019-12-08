package com.github.redouane64.debt.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Debts")
class Debt(
    @PrimaryKey var id: Int?,
    var debtor: String?,
    var dueDate: String?,
    var amount: Float?,
    var currency: String?,
    var debtOrCredit: Int?
) : Serializable