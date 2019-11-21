package com.github.redouane64.debt.services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.github.redouane64.debt.models.DebtItem

class Repository : BaseRepository<DebtItem> {

    private var databaseHelper : SQLiteOpenHelper? = null;

    constructor(context: Context) {
        databaseHelper = DatabaseProvider(context).createDatabaseHelper()
    }

    override fun create(entity: DebtItem) {
        var database = databaseHelper!!.writableDatabase;

        val values = ContentValues().apply {
            put(DatabaseProvider.DebtEntry.COLUMN_NAME, entity.subject);
            put(DatabaseProvider.DebtEntry.COLUMN_AMOUNT, entity.amount);
            put(DatabaseProvider.DebtEntry.COLUMN_CURRENCY, entity.currency);
            put(DatabaseProvider.DebtEntry.COLUMN_DATE, entity.date);
            put(DatabaseProvider.DebtEntry.COLUMN_OWED, entity.owed);
        };

        database.insert(DatabaseProvider.DebtEntry.TABLE_NAME, null, values);
        database.close();
    }

    override fun delete(entity: DebtItem) {
        // TODO:
    }

    override fun getAll(): Array<DebtItem> {
        // TODO:

        return arrayOf();
    }

}