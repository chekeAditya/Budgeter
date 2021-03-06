package com.application.budgeter.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.budgeter.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.application.budgeter.ui.adapters.FragmentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBtn.setOnClickListener {
            startActivity(Intent(this, AddEditItemActivity::class.java))
        }

        viewPager.adapter = FragmentAdapter(supportFragmentManager,lifecycle)
        TabLayoutMediator(tabLayout,viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Income"
                1 -> tab.text = "Expense"
                2 -> tab.text = "Balance"
            }
        }.attach()

    }
}