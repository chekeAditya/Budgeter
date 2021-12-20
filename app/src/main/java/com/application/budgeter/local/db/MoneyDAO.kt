package com.application.budgeter.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.application.budgeter.local.responses.Money

@Dao
interface MoneyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(money: Money)

    @Update
    fun update(money: Money)

    @Delete
    fun delete(money: Money)

    @Query("SELECT * FROM money WHERE category = :category")
    fun get(category: String) : LiveData<List<Money>>

}