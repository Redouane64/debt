package com.github.redouane64.debt.views.create

import com.github.redouane64.debt.presenters.create.CreateDebtPresenter
import com.github.redouane64.debt.views.BaseView

interface CreateDebtView : BaseView<CreateDebtPresenter> {

    fun selectDate();

    fun save();

}