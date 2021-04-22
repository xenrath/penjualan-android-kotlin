package com.xenrath.penjualan.ui.cart.add

import com.xenrath.penjualan.data.model.cart.ResponseCartUpdate

interface CartAddContract {

    interface Presenter {
        fun addCart(username: String, product_id: Long, count: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseCartUpdate: ResponseCartUpdate)
        fun showMessage(message: String)
    }

}