package com.github.redouane64.debt.views

interface BaseView<TPresenter> {
    fun setPresenter(presenter: TPresenter);
}