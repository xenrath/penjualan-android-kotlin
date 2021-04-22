package com.xenrath.penjualan.data.model.login

import com.google.gson.annotations.SerializedName
import com.xenrath.penjualan.data.model.login.DataLogin

data class ResponseLogin(

    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("employee") val login: DataLogin?

)