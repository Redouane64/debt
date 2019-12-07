package com.github.redouane64.debt.models

import java.io.Serializable

class DebtItem : Serializable {

    var subject: String? = null;
    var amount: Float? = null;
    var currency: String? = null;
    var owed: Int? = null;
    var date: Long? = null;

    constructor() {

    }

    constructor(subject: String, amount: Float, currency: String, owed: Int, date: Long) {
        this.subject = subject
        this.amount = amount
        this.currency = currency
        this.owed = owed
        this.date = date
    }

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