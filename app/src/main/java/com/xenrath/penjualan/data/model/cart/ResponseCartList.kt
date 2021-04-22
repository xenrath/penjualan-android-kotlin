package com.xenrath.penjualan.data.model.cart

import com.google.gson.annotations.SerializedName

data class ResponseCartList (

    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: Boolean,
    @SerializedName("data") val dataCart: List<DataCart>

)