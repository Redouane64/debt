package com.github.redouane64.debt.internals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.redouane64.debt.R
import com.github.redouane64.debt.models.DebtItem

class DebtsListAdapter(private val items: Array<DebtItem>) : RecyclerView.Adapter<DebtItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtItemViewHolder {
        return DebtItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.debt_item, parent, false));
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    override fun onBindViewHolder(holder: DebtItemViewHolder, position: Int) {

    }

}