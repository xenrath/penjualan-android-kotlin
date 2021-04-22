package com.xenrath.penjualan.ui.transaction.detail

import com.xenrath.penjualan.data.model.transaction.ResponseTransactionList
import com.xenrath.penjualan.data.model.transaction.detail.ResponseTransactionDetail
import com.xenrath.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionDetailPresenter(val view: TransactionDetailContract.View): TransactionDetailContract.Presenter {

    init {
        view.initFragment()
    }

    override fun getTransactionDetail(invoice: String) {

        view.onLoading(true)

        ApiService.endpoint.getDetailTransaction(invoice).enqueue(object : Callback<ResponseTransactionDetail>{
            override fun onResponse(call: Call<ResponseTransactionDetail>, response: Response<ResponseTransactionDetail>) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseTransactionDetail: ResponseTransactionDetail? = response.body()
                    view.onResult(responseTransactionDetail!!)
                }
            }

            override fun onFailure(call: Call<ResponseTransactionDetail>, t: Throwable) {
                view.onLoading(false)
            }

        })

    }

}