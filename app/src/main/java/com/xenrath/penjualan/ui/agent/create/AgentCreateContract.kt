package com.xenrath.penjualan.ui.agent.create

import android.os.Message
import com.xenrath.penjualan.data.model.agent.ResponseAgentUpdate
import java.io.File

interface AgentCreateContract {

    interface Presenter {
        fun insertAgent(
                store: String,
                owner: String,
                address: String,
                latitude: String,
                longitude: String,
                image: File
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseAgentUpdate: ResponseAgentUpdate)
        fun showMessage(message: String)
    }

}