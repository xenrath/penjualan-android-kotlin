package com.xenrath.penjualan.data.model.agent

import com.google.gson.annotations.SerializedName

data class DataAgent(

    @SerializedName("id") val id: Long?,
    @SerializedName("store") val store: String?,
    @SerializedName("owner") val owner: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("latitude") val latitude: String?,
    @SerializedName("longitude") val longitude: String?,
    @SerializedName("image") val image: String?,

)