package com.application.budgeter.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.application.budgeter.ui.fragments.BalanceFragment
import com.application.budgeter.ui.fragments.ExpenseFragment
import com.application.budgeter.ui.fragments.IncomeFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return IncomeFragment()
            1 -> return ExpenseFragment()
            2 -> return BalanceFragment()
        }
        return IncomeFragment()
    }


}