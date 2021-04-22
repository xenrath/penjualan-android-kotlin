package com.xenrath.penjualan.ui.agent.update

import com.xenrath.penjualan.data.model.agent.ResponseAgentDetail
import com.xenrath.penjualan.data.model.agent.ResponseAgentUpdate
import java.io.File

interface AgentUpdateContract {

    interface Presenter {
        fun getDetail(id: Long)
        fun updateAgent(
            id: Long,
            store: String,
            owner: String,
            address: String,
            latitude: String,
            longitude: String,
            image: File?
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultDetail(responseAgentDetail: ResponseAgentDetail)
        fun onResultUpdate(responseAgentUpdate: ResponseAgentUpdate)
        fun showMessage(message: String)
    }

}