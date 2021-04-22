package com.xenrath.penjualan.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.database.PrefManager
import com.xenrath.penjualan.data.model.login.ResponseLogin
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var presenter: LoginPresenter
    lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
        prefManager = PrefManager(this)
    }

    override fun initActivity() {
        supportActionBar!!.title = "Masuk"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        btn_login.setOnClickListener {
            presenter.doLogin( edt_username.text.toString(), edt_password.text.toString() )
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btn_login.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btn_login.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseLogin: ResponseLogin) {
        presenter.setPref(prefManager, responseLogin.login!!)
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}