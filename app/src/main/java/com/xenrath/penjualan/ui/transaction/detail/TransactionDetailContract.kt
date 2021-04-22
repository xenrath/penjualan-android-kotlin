package com.xenrath.penjualan.ui.transaction.detail

import com.xenrath.penjualan.data.model.transaction.detail.ResponseTransactionDetail

interface TransactionDetailContract {

    interface Presenter {
        fun getTransactionDetail(invoice: String)
    }

    interface View {
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResult(responseTransactionDetail: ResponseTransactionDetail)
    }

}