package com.xenrath.penjualan.ui.transaction.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.model.transaction.detail.DataDetail
import com.xenrath.penjualan.util.GlideHelper
import kotlinx.android.synthetic.main.adapter_transaction_detail.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class TransactionDetailAdapter(val context: Context, var detail: ArrayList<DataDetail> ):
    RecyclerView.Adapter<TransactionDetailAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(detail: DataDetail){
            view.tv_category.text = detail.category
            view.tv_product_name.text = detail.product_name
            view.tv_price.text = "${detail.price_rupiah} x ${detail.count}"

            val total: Double = detail!!.count!!.toDouble() * detail.price!!.toDouble()
            val totalRupiah: String = NumberFormat.getNumberInstance(Locale.GERMANY).format(total)
            view.tv_total.text = "Rp. $totalRupiah"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_transaction_detail, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(detail[position])
        GlideHelper.setImage(context, "http://192.168.1.8/penjualan/public/storage" + detail[position].image!!, holder.view.riv_image)
    }

    override fun getItemCount() = detail.size

    fun setData(newDetailTransaction: List<DataDetail>) {
        detail.clear()
        detail.addAll(newDetailTransaction)
        notifyDataSetChanged()
    }
}