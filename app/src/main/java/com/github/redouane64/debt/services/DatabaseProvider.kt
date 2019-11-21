package com.github.redouane64.debt.services

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DatabaseProvider(private val context: Context) {

    // DatabaseProvider debt table and column names.
    public object DebtEntry : BaseColumns {
        const val TABLE_NAME = "debt";
        const val COLUMN_NAME = "subject_name";
        const val COLUMN_AMOUNT = "amount";
        const val COLUMN_CURRENCY = "currency";
        const val COLUMN_OWED = "owed";
        const val COLUMN_DATE = "date";
    }

    // CREATE TABLE Statement.
    private val CREATE_TABLE = "CREATE TABLE ${DebtEntry.TABLE_NAME} ("              +
                                   "${BaseColumns._ID} integer primary key,"         +
                                   "${DebtEntry.COLUMN_NAME} nvarchar,"              +
                                   "${DebtEntry.COLUMN_AMOUNT} real not null,"       +
                                   "${DebtEntry.COLUMN_CURRENCY} nvarchar not null," +
                                   "${DebtEntry.COLUMN_DATE} integer,"               +
                                   "${DebtEntry.COLUMN_OWED} boolean)";
    // DROP TABLE statement.
    private val DROP_TABLE = "DROP TABLE IF EXISTS ${DebtEntry.TABLE_NAME}";

    // Database name.
    private val DATABASE_NAME = "debts_database";

    // Database version.
    private val DB_VERSION = 1;

    public fun createDatabaseHelper() : DebtDatabaseHelper {
        return DebtDatabaseHelper(this.context);
    }

    inner class DebtDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {

        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_TABLE);
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL(DROP_TABLE);
            onCreate(db);
        }

    }
}