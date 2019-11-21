package com.github.redouane64.debt.models

class DebtItem(val subject : String,
               val amount : Float,
               val currency: String,
               val owed: Int,
               val date: Long) {

    public var id: Int? = null;

    companion object {

        // Database debt table and column names.
        const val TABLE_NAME = "debt";
        const val COLUMN_NAME = "subject_name";
        const val COLUMN_AMOUNT = "amount";
        const val COLUMN_CURRENCY = "currency";
        const val COLUMN_OWED = "owed";
        const val COLUMN_DATE = "date";

    }

}