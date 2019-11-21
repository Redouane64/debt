package com.github.redouane64.debt.models

class DebtItem(val subject : String,
               val amount : Float,
               val currency: String,
               val owed: Int,
               val date: Long) {

    public var id: Int? = null;
}