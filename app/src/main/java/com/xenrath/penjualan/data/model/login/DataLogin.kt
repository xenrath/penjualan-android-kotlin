package com.xenrath.penjualan.data.model.login

import com.google.gson.annotations.SerializedName

data class DataLogin (

    @SerializedName("username") val username: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("is_active") val is_active: String?,

)