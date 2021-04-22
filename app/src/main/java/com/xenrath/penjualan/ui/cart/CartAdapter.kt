package com.xenrath.penjualan.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.Constant
import com.xenrath.penjualan.data.model.cart.DataCart
import com.xenrath.penjualan.util.GlideHelper
import kotlinx.android.synthetic.main.adapter_cart.view.*
import kotlinx.android.synthetic.main.adapter_transaction.view.*
import kotlinx.android.synthetic.main.adapter_transaction.view.tv_total
import okhttp3.internal.notify
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CartAdapter(val context: Context, var cart: ArrayList<DataCart>,
                  val clickListener: (DataCart, Int) -> Unit):
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(cart: DataCart){
            view.tv_category.text = cart.category
            view.tv_product_name.text = cart.product_name
            view.tv_price.text = "${cart.price_rupiah} x ${cart.count}"

            val total: Double = cart!!.count!!.toDouble() * cart.price!!.toDouble()
            val totalRupiah: String = NumberFormat.getNumberInstance(Locale.GERMANY).format(total)
            view.tv_total.text = "Rp. $totalRupiah"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_cart, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(cart[position])
        GlideHelper.setImage(context, Constant.IP_IMAGE + cart[position].image!!, holder.view.riv_image)
        holder.view.iv_delete.setOnClickListener {
            clickListener(cart[position], position)
            removeCart(position)
        }
    }

    override fun getItemCount() = cart.size

    fun setData(newCart: List<DataCart>) {
        cart.clear()
        cart.addAll(newCart)
        notifyDataSetChanged()
    }

    fun removeCart(position: Int){
        cart.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cart.size)
    }

    fun removeAll(){
        cart.clear()
        notifyDataSetChanged()
    }
}