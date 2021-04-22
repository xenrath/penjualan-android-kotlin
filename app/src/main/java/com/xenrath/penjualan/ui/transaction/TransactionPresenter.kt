package com.xenrath.penjualan.ui.transaction

import com.xenrath.penjualan.data.model.transaction.ResponseTransactionList
import com.xenrath.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionPresenter(val view: TransactionContract.View): TransactionContract.Presenter {

    init {
        view.initFragment()
    }

    override fun getTransaction(username: String) {
        view.onLoading(true)
        ApiService.endpoint.getTransaction(username).enqueue(object : Callback<ResponseTransactionList>{
            override fun onResponse(
                call: Call<ResponseTransactionList>,
                response: Response<ResponseTransactionList>
            ) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseTransactionList: ResponseTransactionList? = response.body()
                    view.onResult(responseTransactionList!!)
                }
            }

            override fun onFailure(call: Call<ResponseTransactionList>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

}