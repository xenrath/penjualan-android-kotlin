package com.xenrath.penjualan.ui.agent.search

import com.xenrath.penjualan.data.model.agent.ResponseAgentList

interface AgentSearchContract {

    interface Presenter {
        fun getAgent()
        fun searchAgent(keyword: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(loading: Boolean)
        fun onResultAgent(responseAgentList: ResponseAgentList)
    }

}