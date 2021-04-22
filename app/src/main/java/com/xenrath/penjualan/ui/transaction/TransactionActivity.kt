package com.xenrath.penjualan.ui.transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xenrath.penjualan.R
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initActivity();
    }

    fun initActivity() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, TransactionFragment(), "transFrag")
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.findFragmentByTag("transFrag") == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TransactionFragment(), "transFrag")
                .commit()
        } else {
            finish()
        }
        return super.onSupportNavigateUp()
    }
}