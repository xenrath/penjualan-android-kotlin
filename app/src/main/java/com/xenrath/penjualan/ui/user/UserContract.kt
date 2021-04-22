package com.xenrath.penjualan.ui.user

import com.xenrath.penjualan.data.database.PrefManager

interface UserContract {

    interface Presenter {
        fun doLogin(prefManager: PrefManager)
        fun doLogout(prefManager: PrefManager)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onResultLogin(prefManager: PrefManager)
        fun onResultLogout()
        fun showMessage(message: String)
    }

}