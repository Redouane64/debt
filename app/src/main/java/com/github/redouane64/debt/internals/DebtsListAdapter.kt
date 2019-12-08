package com.github.redouane64.debt.internals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.redouane64.debt.R
import com.github.redouane64.debt.models.Debt
import java.text.SimpleDateFormat
import java.util.*

class DebtsListAdapter(private val items: Array<Debt>) : RecyclerView.Adapter<DebtItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtItemViewHolder {
        return DebtItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.debt_item, parent, false));
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    override fun onBindViewHolder(holder: DebtItemViewHolder, position: Int) {
        val item = items[position];
        val today = SimpleDateFormat("YYYY.M.d").format(Date());

        if(item.dueDate.equals(today)) {
            holder.setAsTodayDebt();
        }

        holder.setSubjectName(item.debtor!!);
        holder.setAmount(item.amount.toString(), item.currency!!);
        holder.setDate(item.dueDate!!);
    }

}