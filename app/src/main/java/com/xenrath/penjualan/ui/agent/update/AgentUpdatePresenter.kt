package com.xenrath.penjualan.ui.agent.update

import com.xenrath.penjualan.data.model.agent.ResponseAgentDetail
import com.xenrath.penjualan.data.model.agent.ResponseAgentUpdate
import com.xenrath.penjualan.network.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AgentUpdatePresenter(val view: AgentUpdateContract.View): AgentUpdateContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getDetail(id: Long) {
        view.onLoading(true)
        ApiService.endpoint.getAgentDetail(id).enqueue(object : Callback<ResponseAgentDetail>{
            override fun onResponse(
                call: Call<ResponseAgentDetail>,
                response: Response<ResponseAgentDetail>
            ) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseAgentDetail: ResponseAgentDetail? = response.body()
                    view.onResultDetail(responseAgentDetail!!)
                }
            }

            override fun onFailure(call: Call<ResponseAgentDetail>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun updateAgent(
        id: Long,
        store: String,
        owner: String,
        address: String,
        latitude: String,
        longitude: String,
        image: File?
    ) {
        val requestBody: RequestBody
        val multipartBody: MultipartBody.Part

        if (image != null){
            requestBody = image.asRequestBody("image/*".toMediaTypeOrNull())
            multipartBody = MultipartBody.Part.createFormData("image", image.name, requestBody)
        } else {
            requestBody = "".toRequestBody("image/*".toMediaTypeOrNull())
            multipartBody = MultipartBody.Part.createFormData("image", "", requestBody)
        }

        view.onLoading(true)
        ApiService.endpoint.updateAgent(
            id,
            store,
            owner,
            address,
            latitude,
            longitude,
            multipartBody,
            "PUT"
        ).enqueue(object : Callback<ResponseAgentUpdate>{
            override fun onResponse(
                call: Call<ResponseAgentUpdate>,
                response: Response<ResponseAgentUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseAgentUpdate: ResponseAgentUpdate? = response.body()
                    view.onResultUpdate(responseAgentUpdate!!)
                }
            }

            override fun onFailure(call: Call<ResponseAgentUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}