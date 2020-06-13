package com.guilhermecardoso.currencychallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.guilhermecardoso.currencychallenge.exchangeDashboard.ExchangeDashboardFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, ExchangeDashboardFragment())
                .commit()
        }
    }
}