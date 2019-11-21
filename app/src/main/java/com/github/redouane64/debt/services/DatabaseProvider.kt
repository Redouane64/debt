package com.github.redouane64.debt.services

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.github.redouane64.debt.models.DebtItem

class DatabaseProvider(private val context: Context) {

    // CREATE TABLE Statement.
    private val CREATE_TABLE = "CREATE TABLE ${DebtItem.TABLE_NAME} ("          +
                               "${BaseColumns._ID} integer primary key,"        +
                               "${DebtItem.COLUMN_NAME} nvarchar,"              +
                               "${DebtItem.COLUMN_AMOUNT} real not null,"       +
                               "${DebtItem.COLUMN_CURRENCY} nvarchar not null," +
                               "${DebtItem.COLUMN_DATE} integer,"               +
                               "${DebtItem.COLUMN_OWED} boolean)";
    // DROP TABLE statement.
    private val DROP_TABLE = "DROP TABLE IF EXISTS ${DebtItem.TABLE_NAME}";

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