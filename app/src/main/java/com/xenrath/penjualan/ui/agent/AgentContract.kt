package com.xenrath.penjualan.ui.agent

import com.xenrath.penjualan.data.model.agent.DataAgent
import com.xenrath.penjualan.data.model.agent.ResponseAgentList
import com.xenrath.penjualan.data.model.agent.ResponseAgentUpdate

interface AgentContract {

    interface Presenter {
        fun getAgent()
        fun deleteAgent(id: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(loading: Boolean)
        fun onResultAgent(responseAgentList: ResponseAgentList)
        fun onResultDelete(responseAgentUpdate: ResponseAgentUpdate)
        fun showDialogDelete(dataAgent: DataAgent, position: Int)
        fun showDialogDetail(dataAgent: DataAgent, position: Int)
        fun showMessage(message: String)
    }

}