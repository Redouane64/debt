package com.github.redouane64.debt.services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.github.redouane64.debt.models.DebtItem

class Repository(context: Context) : BaseRepository<DebtItem> {

    private var databaseHelper : SQLiteOpenHelper? = null;

    init {
        databaseHelper = DatabaseProvider(context).createDatabaseHelper()
    }

    override fun create(entity: DebtItem) {
        val database = databaseHelper!!.writableDatabase;

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

    override fun delete(id: Int) {
        val database = databaseHelper!!.writableDatabase;

        val delRow = database.delete(
            DatabaseProvider.DebtEntry.TABLE_NAME,
            "${BaseColumns._ID} = ?",
            arrayOf(id.toString())
        );

        database.close();
    }

    override fun getAll(): Array<DebtItem> {
        val database = databaseHelper!!.readableDatabase;

        val cursor = database.query(
            DatabaseProvider.DebtEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        );

        val items = mutableListOf<DebtItem>();

        with(cursor) {
            while (cursor.moveToNext()) {
                val item = DebtItem(
                    getString(cursor.getColumnIndex(DatabaseProvider.DebtEntry.COLUMN_NAME)),
                    getFloat(cursor.getColumnIndex(DatabaseProvider.DebtEntry.COLUMN_AMOUNT)),
                    getString(cursor.getColumnIndex(DatabaseProvider.DebtEntry.COLUMN_CURRENCY)),
                    getInt(cursor.getColumnIndex(DatabaseProvider.DebtEntry.COLUMN_OWED)),
                    getLong(cursor.getColumnIndex(DatabaseProvider.DebtEntry.COLUMN_DATE))
                ).also {
                    it.id = getInt(cursor.getColumnIndex(BaseColumns._ID))
                };

                items.add(item);
            }
        }

        cursor.close();
        database.close();

        return items.toTypedArray();
    }

}