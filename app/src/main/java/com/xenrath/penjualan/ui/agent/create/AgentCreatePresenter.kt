package com.xenrath.penjualan.ui.agent.create

import com.xenrath.penjualan.data.model.agent.ResponseAgentUpdate
import com.xenrath.penjualan.network.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AgentCreatePresenter(val view: AgentCreateContract.View): AgentCreateContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun insertAgent(
        store: String,
        owner: String,
        address: String,
        latitude: String,
        longitude: String,
        image: File
    ) {

        val requestBody: RequestBody = image.asRequestBody("image/*".toMediaTypeOrNull())
        val multipartBody: MultipartBody.Part? = MultipartBody.Part.createFormData("image", image.name, requestBody)

        view.onLoading(true)
        ApiService.endpoint.insertAgent(store, owner, address, latitude, longitude, multipartBody!!)
                .enqueue(object : Callback<ResponseAgentUpdate>{
                    override fun onResponse(call: Call<ResponseAgentUpdate>, response: Response<ResponseAgentUpdate>) {
                        view.onLoading(false)
                        if (response.isSuccessful){
                            val responseAgentUpdate: ResponseAgentUpdate? = response.body()
                            view.onResult(responseAgentUpdate!!)
                        }
                    }

                    override fun onFailure(call: Call<ResponseAgentUpdate>, t: Throwable) {
                        view.onLoading(false)
                    }

                })

    }
}