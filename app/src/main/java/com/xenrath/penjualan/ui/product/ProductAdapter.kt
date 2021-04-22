package com.xenrath.penjualan.ui.product

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.Constant
import com.xenrath.penjualan.data.model.product.DataProduct
import com.xenrath.penjualan.util.GlideHelper
import kotlinx.android.synthetic.main.adapter_product.view.*
import java.util.*
import kotlin.collections.ArrayList

class ProductAdapter(val context: Context, var product: ArrayList<DataProduct>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bing(product: DataProduct){
            view.tv_name.text = product.name
            view.tv_price.text = product.price_rupiah
            view.tv_stock.text = "Stok tersedia (${product.stock})"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_product, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(product[position])
        GlideHelper.setImage(context, "http://192.168.1.8/penjualan/public/storage" + product[position].image!!, holder.view.riv_image)
        holder.view.ll_product.setOnClickListener {
            Constant.PRODUCT_ID = product[position].id!!
            Constant.PRODUCT_NAME = product[position].name!!
            Constant.IS_CHANGED = true
            (context as Activity).finish()
        }
    }

    override fun getItemCount() = product.size

    fun setData(newProduct: List<DataProduct>) {
        product.clear()
        product.addAll(newProduct!!)
        notifyDataSetChanged()
    }
}