package com.xenrath.penjualan.ui.cart.add

import com.xenrath.penjualan.data.model.cart.ResponseCartUpdate
import com.xenrath.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartAddPresenter(val view: CartAddContract.View): CartAddContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun addCart(username: String, product_id: Long, count: Long) {
        view.onLoading(true)
        ApiService.endpoint.addCart(username, product_id, count).enqueue(object : Callback<ResponseCartUpdate>{
            override fun onResponse(call: Call<ResponseCartUpdate>, response: Response<ResponseCartUpdate>) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseCartUpdate: ResponseCartUpdate? = response.body()
                    view.showMessage(responseCartUpdate!!.message)
                    view.onResult(responseCartUpdate)
                }
            }

            override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }


}