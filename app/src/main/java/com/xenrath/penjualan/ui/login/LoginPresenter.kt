package com.xenrath.penjualan.ui.login

import com.xenrath.penjualan.data.database.PrefManager
import com.xenrath.penjualan.data.model.login.DataLogin
import com.xenrath.penjualan.data.model.login.ResponseLogin
import com.xenrath.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val view: LoginContract.View): LoginContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun doLogin(username: String, password: String) {
        view.onLoading(true)
        ApiService.endpoint.loginEmployee(username, password)
                .enqueue(object : Callback<ResponseLogin> {
                    override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>
                    ) {
                        view.onLoading(false)
                        if (response.isSuccessful) {
                            val responseLogin: ResponseLogin? = response.body()
                            view.showMessage(responseLogin!!.message)

                            if (responseLogin.status){
                                view.onResult(responseLogin)
                            }

                        }
                    }

                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        view.onLoading(false)
                    }

                })
    }

    override fun setPref(prefManager: PrefManager, dataLogin: DataLogin) {
        prefManager.prefIsLogin = true
        prefManager.prefUsername = dataLogin.username!!
        prefManager.prefName = dataLogin.name!!
        prefManager.prefGender = dataLogin.gender!!
        prefManager.prefAddress = dataLogin.address!!
        prefManager.prefIsActive = dataLogin.is_active!!
    }

}