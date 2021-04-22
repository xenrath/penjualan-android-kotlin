package com.xenrath.penjualan.data.model.agent

import com.google.gson.annotations.SerializedName
import com.xenrath.penjualan.data.model.agent.DataAgent

data class ResponseAgentList (

    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val dataAgent: List<DataAgent>

)