package com.xenrath.penjualan.ui.user

import com.xenrath.penjualan.data.database.PrefManager

class UserPresenter(val view: UserContract.View): UserContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun doLogin(prefManager: PrefManager) {
        if (prefManager.prefIsLogin) view.onResultLogin(prefManager)
    }

    override fun doLogout(prefManager: PrefManager) {
        prefManager.logout()
        view.showMessage("Berhasil keluar")
        view.onResultLogout()
    }
}