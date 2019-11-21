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
            put(DebtItem.COLUMN_NAME, entity.subject);
            put(DebtItem.COLUMN_AMOUNT, entity.amount);
            put(DebtItem.COLUMN_CURRENCY, entity.currency);
            put(DebtItem.COLUMN_DATE, entity.date);
            put(DebtItem.COLUMN_OWED, entity.owed);
        };

        database.insert(DebtItem.TABLE_NAME, null, values);
        database.close();
    }

    override fun delete(id: Int) {
        val database = databaseHelper!!.writableDatabase;

        val delRow = database.delete(
            DebtItem.TABLE_NAME,
            "${BaseColumns._ID} = ?",
            arrayOf(id.toString())
        );

        database.close();
    }

    override fun getAll(): Array<DebtItem> {
        val database = databaseHelper!!.readableDatabase;

        val cursor = database.query(
            DebtItem.TABLE_NAME,
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
                    getString(cursor.getColumnIndex(DebtItem.COLUMN_NAME)),
                    getFloat(cursor.getColumnIndex(DebtItem.COLUMN_AMOUNT)),
                    getString(cursor.getColumnIndex(DebtItem.COLUMN_CURRENCY)),
                    getInt(cursor.getColumnIndex(DebtItem.COLUMN_OWED)),
                    getLong(cursor.getColumnIndex(DebtItem.COLUMN_DATE))
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