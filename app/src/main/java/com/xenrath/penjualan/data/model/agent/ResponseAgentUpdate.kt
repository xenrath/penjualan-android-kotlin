package com.xenrath.penjualan.data.model.agent

import com.google.gson.annotations.SerializedName

data class ResponseAgentUpdate (
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String
)