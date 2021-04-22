package com.xenrath.penjualan.ui.agent.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.Constant
import com.xenrath.penjualan.data.model.agent.DataAgent
import com.xenrath.penjualan.util.GlideHelper
import kotlinx.android.synthetic.main.adapter_agent_search.view.*

class AgentSearchAdapter(val context: Context, var dataAgent: ArrayList<DataAgent>,
                         val clickListener: (DataAgent, Int) -> Unit):
    RecyclerView.Adapter<AgentSearchAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(dataAgent: DataAgent){
            view.tv_store.text = dataAgent.store
            view.tv_location.text = dataAgent.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_agent_search, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataAgent[position])

        GlideHelper.setImage(context, "http://192.168.1.7/penjualan/public/storage" + dataAgent[position].image!!, holder.view.riv_image)
        holder.view.cv_agent.setOnClickListener{
            Constant.AGENT_ID = dataAgent[position].id!!
            clickListener(dataAgent[position], position)
        }
    }

    override fun getItemCount() = dataAgent.size

    fun setData(newDataAgent: List<DataAgent>) {
        dataAgent.clear()
        dataAgent.addAll(newDataAgent)
        notifyDataSetChanged()
    }

}