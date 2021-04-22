package com.xenrath.penjualan.ui.cart

import com.xenrath.penjualan.data.model.cart.ResponseCartList
import com.xenrath.penjualan.data.model.cart.ResponseCartUpdate
import com.xenrath.penjualan.data.model.cart.ResponseCheckout

interface CartContract {

    interface Presenter {
        fun getCart(username: String)

        fun deleteItemCart(id: Long)
        fun deleteCart(username: String)

        fun checkOut(username: String, agent_id: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingCart(loading: Boolean)
        fun onResultCart(responseCartList: ResponseCartList)
        fun showMesage(message: String)

        fun onResultDelete(responseCartUpdate: ResponseCartUpdate)
        fun showDialog()

        fun onLoadingCheckout(loading: Boolean)
        fun onResultCheckout(responseCheckout: ResponseCheckout)
    }

}