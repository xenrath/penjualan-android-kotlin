package com.xenrath.penjualan.data.model.agent

import android.os.Message
import com.google.gson.annotations.SerializedName

data class ResponseAgentDetail (
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("agent") val dataAgent: DataAgent
)