package com.xenrath.penjualan.ui.home

class MainPresenter(val view: MainContract.View) {

    init {
        view.initListener()
    }

}