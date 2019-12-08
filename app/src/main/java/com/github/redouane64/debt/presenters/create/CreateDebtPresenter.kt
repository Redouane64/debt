package com.github.redouane64.debt.presenters.create

import com.github.redouane64.debt.models.Debt
import com.github.redouane64.debt.presenters.BasePresenter
import com.github.redouane64.debt.views.create.CreateDebtView

class CreateDebtPresenter(private var view: CreateDebtView?) : BasePresenter {

    private var debt: Debt? = Debt(null, null, null, null, null, null);

    fun setDebtor(debtor: String) {
        debt!!.debtor = debtor;
    }

    fun setIamDebtor(value: Int) {
        debt!!.debtOrCredit = value;
    }

    fun setDate(value: String) {
        debt!!.dueDate = value;
    }

    fun setCurrency(value: String) {
        debt!!.currency = value;
    }

    fun setAmount(value: Float) {
        debt!!.amount = value;
    }

    fun save() : Debt {
        return this.debt!!;
    }

    override fun onDestroy() {
        if (view != null)
            view = null;

        this.debt = null;
    }
}