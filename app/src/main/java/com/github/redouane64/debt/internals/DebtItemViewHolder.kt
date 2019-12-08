package com.github.redouane64.debt.internals


import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.redouane64.debt.R
import kotlinx.android.synthetic.main.debt_item.view.*

class DebtItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun setDate(date: String) {
        view.dateTextView.text = date;
    }

    fun setAmount(amount: String, currency: String) {
        view.amountTextView.text = "$amount $currency";
    }

    fun setSubjectName(subject: String) {
        view.subjectNameTextView.text = subject;
    }

    fun setAsTodayDebt() {
        view.itemCardView.setBackgroundResource((R.color.debt_today_color));
        view.dateTextView.setTextColor(Color.WHITE);
        view.amountTextView.setTextColor(Color.WHITE);
        view.subjectNameTextView.setTextColor(Color.WHITE);
    }
}