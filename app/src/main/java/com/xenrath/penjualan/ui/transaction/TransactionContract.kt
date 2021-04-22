package com.xenrath.penjualan.ui.transaction

import com.xenrath.penjualan.data.model.transaction.ResponseTransactionList

interface TransactionContract {

    interface Presenter {
        fun getTransaction(username: String)
    }

    interface View {
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResult(responseTransactionList: ResponseTransactionList)
        fun onClickTransaction(invoice: String)
    }

}