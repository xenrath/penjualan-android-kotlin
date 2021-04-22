package com.xenrath.penjualan.ui.cart

import com.xenrath.penjualan.data.model.cart.ResponseCartList
import com.xenrath.penjualan.data.model.cart.ResponseCartUpdate
import com.xenrath.penjualan.data.model.cart.ResponseCheckout
import com.xenrath.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartPresenter(val view: CartContract.View): CartContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getCart(username: String) {
        view.onLoadingCart(true)
        ApiService.endpoint.getCart(username).enqueue(object : Callback<ResponseCartList>{
            override fun onResponse(call: Call<ResponseCartList>, response: Response<ResponseCartList>) {
                view.onLoadingCart(false)
                if (response.isSuccessful){
                    val responseCartList: ResponseCartList? = response.body()
                    view.onResultCart(responseCartList!!)
                }
            }

            override fun onFailure(call: Call<ResponseCartList>, t: Throwable) {
                view.onLoadingCart(false)
            }

        })
    }

    override fun deleteItemCart(id: Long) {
        ApiService.endpoint.deleteItemCart(id).enqueue(object : Callback<ResponseCartUpdate> {
            override fun onResponse(call: Call<ResponseCartUpdate>, response: Response<ResponseCartUpdate>) {
                view.onLoadingCart(false)
                if (response.isSuccessful){
                    val responseCartUpdate: ResponseCartUpdate? = response.body()
                    view.onResultDelete(responseCartUpdate!!)
                }
            }

            override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {
                view.onLoadingCart(false)
            }

        })
    }

    override fun deleteCart(username: String) {
        ApiService.endpoint.deleteCart(username).enqueue(object : Callback<ResponseCartUpdate> {
            override fun onResponse(call: Call<ResponseCartUpdate>, response: Response<ResponseCartUpdate>) {
                view.onLoadingCart(false)
                if (response.isSuccessful){
                    val responseCartUpdate: ResponseCartUpdate? = response.body()
                    view.onResultDelete(responseCartUpdate!!)
                    view.showMesage(responseCartUpdate.message)
                }
            }

            override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {
                view.onLoadingCart(false)
            }

        })
    }

    override fun checkOut(username: String, agent_id: Long) {
        view.onLoadingCheckout(true)
        ApiService.endpoint.checkOut(username, agent_id).enqueue(object : Callback<ResponseCheckout>{
            override fun onResponse(call: Call<ResponseCheckout>, response: Response<ResponseCheckout>) {
                view.onLoadingCheckout(false)
                if (response.isSuccessful){
                    val responseCheckout: ResponseCheckout? = response.body()
                    view.onResultCheckout(responseCheckout!!)
                    view.showMesage(responseCheckout.message)
                }
            }

            override fun onFailure(call: Call<ResponseCheckout>, t: Throwable) {
                view.onLoadingCheckout(false)
            }

        })
    }

}