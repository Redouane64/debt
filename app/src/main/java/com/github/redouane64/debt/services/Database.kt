package com.github.redouane64.debt.services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class Database {

    object DebtEntry : BaseColumns {
        const val TABLE_NAME = "debt";
        const val COLUMN_NAME = "subject_name";
        const val COLUMN_AMOUNT = "amount";
        const val COLUMN_CURRENCY = "currency";
        const val COLUMN_OWED = "owed";
        const val COLUMN_DATE = "date";
    }

    private val TABLE_SCHEMA_DEF = "CREATE TABLE ${DebtEntry.TABLE_NAME} ("          +
                                   "${BaseColumns._ID} integer primary key,"         +
                                   "${DebtEntry.COLUMN_NAME} nvarchar,"              +
                                   "${DebtEntry.COLUMN_AMOUNT} real not null,"       +
                                   "${DebtEntry.COLUMN_CURRENCY} nvarchar not null," +
                                   "${DebtEntry.COLUMN_DATE} integer,"               +
                                   "${DebtEntry.COLUMN_OWED} boolean)";
    private val DATABASE_NAME = "debts_database";
    private val DB_VERSION = 1;

    public fun insert() {
        val values = ContentValues().apply {
            put(DebtEntry.COLUMN_NAME, name)
        }
    }

    inner class DebtDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {

        override fun onCreate(db: SQLiteDatabase?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}