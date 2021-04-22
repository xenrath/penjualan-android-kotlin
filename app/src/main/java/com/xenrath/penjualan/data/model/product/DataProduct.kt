package com.xenrath.penjualan.data.model.product

import com.google.gson.annotations.SerializedName

data class DataProduct (

    @SerializedName("id") val id: Long?,
    @SerializedName("category_id") val category_id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("price") val price: String?,
    @SerializedName("price_rupiah") val price_rupiah: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("stock") val stock: String?,
    @SerializedName("category") val category: String?,

)