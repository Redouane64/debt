package com.github.redouane64.debt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.redouane64.debt.models.Debt

@Database(entities = [Debt::class], version = 1)
abstract class DebtsDatabase : RoomDatabase() {

    companion object {
        private var debtsDatabase: DebtsDatabase? = null;
        fun getInstance(context: Context) : DebtsDatabase {
            if (debtsDatabase == null) {
                debtsDatabase = Room.databaseBuilder(context, DebtsDatabase::class.java, "debts.db")
                    .allowMainThreadQueries() // This bad, but i do not want to care for now.
                    .build();
                return debtsDatabase!!;
            } else {
                return debtsDatabase!!;
            }
        }
    }

    abstract fun Debts() : Debts;
}
