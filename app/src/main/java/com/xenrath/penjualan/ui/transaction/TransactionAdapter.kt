package com.xenrath.penjualan.ui.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.model.transaction.DataTransaction
import kotlinx.android.synthetic.main.adapter_transaction.view.*

class TransactionAdapter(val context: Context, var transaction: ArrayList<DataTransaction>,
                         val clickListener: (DataTransaction, Int) -> Unit):
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(transaction: DataTransaction){
            view.tv_invoice.text = transaction.invoice_number
            view.tv_date.text = transaction.date
            view.tv_total.text = transaction.total
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_transaction, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(transaction[position])

        holder.view.tv_detail.setOnClickListener {
            clickListener(transaction[position], position)
        }
    }

    override fun getItemCount() = transaction.size

    fun setData(newTransaction: List<DataTransaction>) {
        transaction.clear()
        transaction.addAll(newTransaction)
        notifyDataSetChanged()
    }
}