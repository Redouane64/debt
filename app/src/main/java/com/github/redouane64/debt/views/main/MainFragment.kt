package com.github.redouane64.debt.views.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.redouane64.debt.R
import com.github.redouane64.debt.database.DebtsDatabase
import com.github.redouane64.debt.internals.DateHelper
import com.github.redouane64.debt.internals.DebtsListAdapter

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val database = DebtsDatabase.getInstance(inflater.context);

        val view = inflater.inflate(R.layout.main_fragment, container, false);
        val listView = view.findViewById<RecyclerView>(R.id.debts_recyclerview);

        val todaysDebts  = database.Debts().getByDate(DateHelper.Today);

        listView.adapter = DebtsListAdapter(todaysDebts.toTypedArray());
        listView.layoutManager = LinearLayoutManager(inflater.context);

        return view;
    }
}