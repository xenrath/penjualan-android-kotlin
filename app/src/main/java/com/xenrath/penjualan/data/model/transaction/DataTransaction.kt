package com.xenrath.penjualan.data.model.transaction

import com.google.gson.annotations.SerializedName

data class DataTransaction (

    @SerializedName("id") val id: Long?,
    @SerializedName("invoice_number") val invoice_number: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("agent_id") val agent_id: Long?,
    @SerializedName("username") val username: String?,
    @SerializedName("total") val total: String?,
    @SerializedName("total_rupiah") val total_rupiah: String?,

)