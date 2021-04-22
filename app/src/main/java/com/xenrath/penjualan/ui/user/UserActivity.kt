package com.xenrath.penjualan.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.database.PrefManager
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), UserContract.View {

    lateinit var prefManager: PrefManager
    lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        prefManager = PrefManager(this)
        presenter = UserPresenter(this)
        presenter.doLogin(prefManager)
    }

    override fun initActivity() {
        supportActionBar!!.hide()
    }

    override fun initListener() {
        btn_back.setOnClickListener {
            finish()
        }
        tv_logout.setOnClickListener {
            presenter.doLogout(prefManager)
        }
    }

    override fun onResultLogin(prefManager: PrefManager) {
        tv_username.text = prefManager.prefUsername
        tv_name.text = prefManager.prefName
        tv_gender.text = prefManager.prefGender
        tv_address.text = prefManager.prefAddress
    }

    override fun onResultLogout() {
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}