package com.xenrath.penjualan.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.database.PrefManager
import com.xenrath.penjualan.ui.agent.AgentActivity
import com.xenrath.penjualan.ui.login.LoginActivity
import com.xenrath.penjualan.ui.transaction.TransactionActivity
import com.xenrath.penjualan.ui.user.UserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), MainContract.View {

    lateinit var prefManager: PrefManager
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefManager = PrefManager(this)
        presenter = MainPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        when (prefManager.prefIsLogin){
            true -> {
                cv_profile.visibility = View.VISIBLE
                btn_login.visibility = View.GONE
            }
            false -> {
                cv_profile.visibility = View.GONE
                btn_login.visibility = View.VISIBLE
            }
        }
    }

    override fun initListener() {
        cv_transaction.setOnClickListener {
            if (prefManager.prefIsLogin) {
                startActivity(Intent(this, TransactionActivity::class.java))
            } else {
                showMessage("Anda belum login")
            }
        }
        cv_agent.setOnClickListener {
            if (prefManager.prefIsLogin) {
                startActivity(Intent(this, AgentActivity::class.java))
            } else {
                showMessage("Anda belum login")
            }
        }
        cv_profile.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
        btn_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}