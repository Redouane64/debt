package com.github.redouane64.debt.presenters.create

import com.github.redouane64.debt.models.DebtItem
import com.github.redouane64.debt.presenters.BasePresenter
import com.github.redouane64.debt.views.create.CreateDebtView

class CreateDebtPresenter(private var view: CreateDebtView?) : BasePresenter {

    private var debt: DebtItem? = DebtItem();

    fun setDebtor(debtor: String) {
        debt!!.subject = debtor;
    }

    fun setIamDebtor(value: Int) {
        debt!!.owed = value;
    }

    fun setDate(value: Long) {
        debt!!.date = value;
    }

    fun setCurrency(value: String) {
        debt!!.currency = value;
    }

    fun setAmount(value: Float) {
        debt!!.amount = value;
    }

    fun save() : DebtItem {
        return this.debt!!;
    }

    override fun onDestroy() {
        if (view != null)
            view = null;

        this.debt = null;
    }
}