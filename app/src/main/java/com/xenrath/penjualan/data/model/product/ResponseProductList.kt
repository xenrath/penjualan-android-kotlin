package com.xenrath.penjualan.data.model.product

import com.google.gson.annotations.SerializedName

data class ResponseProductList (

    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: Boolean,
    @SerializedName("data") val dataProduct: List<DataProduct>,

)