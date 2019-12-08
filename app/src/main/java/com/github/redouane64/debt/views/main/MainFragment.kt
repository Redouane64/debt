package com.github.redouane64.debt.views.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.redouane64.debt.R
import com.github.redouane64.debt.internals.DebtsListAdapter
import com.github.redouane64.debt.database.DebtsDatabase
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val database = DebtsDatabase.getInstance(inflater.context);

        val view = inflater.inflate(R.layout.main_fragment, container, false);
        val listView = view.findViewById<RecyclerView>(R.id.debts_recyclerview);

        val today = SimpleDateFormat("YYYY.M.d").format(Date())
        val todaysDebts  = database.Debts().getByDate(today);

        listView.adapter = DebtsListAdapter(todaysDebts.toTypedArray());
        listView.layoutManager = LinearLayoutManager(inflater.context);

        return view;
    }
}