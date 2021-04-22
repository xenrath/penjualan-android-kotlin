package com.xenrath.penjualan.ui.agent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.Constant
import com.xenrath.penjualan.data.model.agent.DataAgent
import com.xenrath.penjualan.util.GlideHelper
import kotlinx.android.synthetic.main.activity_agent_create.view.*
import kotlinx.android.synthetic.main.adapter_agent.view.*

class AgentAdapter(val context: Context, var dataAgent: ArrayList<DataAgent>,
                   val clickListener: (DataAgent, Int, String) -> Unit):
    RecyclerView.Adapter<AgentAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(dataAgent: DataAgent){
            view.tv_store.text = dataAgent.store
            view.tv_location.text = dataAgent.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_agent, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataAgent[position])

        GlideHelper.setImage(context, Constant.IP_IMAGE + dataAgent[position].image!!, holder.view.riv_image)
        holder.view.riv_image.setOnClickListener{
            Constant.AGENT_ID = dataAgent[position].id!!
            clickListener(dataAgent[position], position, "detail")
        }

        holder.view.tv_option.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.view.tv_option)
            popupMenu.inflate(R.menu.menu_options)
            popupMenu.setOnMenuItemClickListener { 
                when(it.itemId){
                    R.id.action_update -> {
                        Constant.AGENT_ID = dataAgent[position].id!!
                        clickListener(dataAgent[position], position, "update")
                    }
                    R.id.action_delete -> {
                        Constant.AGENT_ID = dataAgent[position].id!!
                        clickListener(dataAgent[position], position, "delete")
                    }
                }
                true
            }
            popupMenu.show()
        }
    }

    override fun getItemCount() = dataAgent.size

    fun setData(newDataAgent: List<DataAgent>) {
        dataAgent.clear()
        dataAgent.addAll(newDataAgent)
        notifyDataSetChanged()
    }

    fun removeAgent(position: Int){
        dataAgent.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataAgent.size)
    }

}