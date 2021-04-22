package com.xenrath.penjualan.data.model.product

import com.google.gson.annotations.SerializedName

data class DataCategory (

    @SerializedName("id") val id: Long?,
    @SerializedName("category") val category: String?,
    @SerializedName("image") val image: String?,

)