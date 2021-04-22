package com.xenrath.penjualan.data.model.transaction.detail

import com.google.gson.annotations.SerializedName

data class DataDetail (

    @SerializedName("id") val id: Long?,
    @SerializedName("invoice_number") val invoice_number: String?,
    @SerializedName("product_id") val product_id: String?,
    @SerializedName("count") val count: String?,
    @SerializedName("price") val price: String?,
    @SerializedName("price_rupiah") val price_rupiah: String?,
    @SerializedName("product_name") val product_name: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("image") val image: String?,

)