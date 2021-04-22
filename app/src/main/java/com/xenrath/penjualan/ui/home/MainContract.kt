package com.xenrath.penjualan.ui.home

interface MainContract {

    interface View {
        fun initListener()
        fun showMessage(message: String)
    }

}