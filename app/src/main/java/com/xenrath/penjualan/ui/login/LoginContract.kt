package com.xenrath.penjualan.ui.login

import com.xenrath.penjualan.data.database.PrefManager
import com.xenrath.penjualan.data.model.login.DataLogin
import com.xenrath.penjualan.data.model.login.ResponseLogin

interface LoginContract {

    interface Presenter {
        fun doLogin(username: String, password: String)
        fun setPref(prefManager: PrefManager, dataLogin: DataLogin)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseLogin: ResponseLogin)
        fun showMessage(message: String)
    }

}