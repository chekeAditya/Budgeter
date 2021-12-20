package com.application.budgeter.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.application.budgeter.local.responses.Money

@Database(entities = [Money::class],version = 1)
abstract class MoneyDatabase: RoomDatabase() {

    abstract fun getMoneyDAO() : MoneyDAO

}