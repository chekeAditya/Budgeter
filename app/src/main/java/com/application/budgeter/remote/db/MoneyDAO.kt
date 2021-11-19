package com.application.budgeter.remote.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.application.budgeter.remote.responses.Money

@Dao
interface MoneyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(money: Money)

    @Update
    fun update(money: Money)

    @Delete
    fun delete(money: Money)

    @Query("SELECT * FROM money WHERE category = :category")
    fun get(category: String) : LiveData<List<Money>>

}