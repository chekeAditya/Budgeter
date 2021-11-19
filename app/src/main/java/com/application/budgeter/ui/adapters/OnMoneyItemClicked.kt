package com.application.budgeter.ui.adapters

import com.application.budgeter.remote.responses.Money

interface OnMoneyItemClicked {

    fun onClick(money: Money)

}