package com.application.budgeter.ui.adapters

import com.application.budgeter.local.responses.Money

interface OnMoneyItemClicked {

    fun onClick(money: Money)

}