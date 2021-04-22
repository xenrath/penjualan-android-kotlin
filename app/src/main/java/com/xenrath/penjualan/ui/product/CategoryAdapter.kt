package com.xenrath.penjualan.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.Constant
import com.xenrath.penjualan.data.model.product.DataCategory
import com.xenrath.penjualan.util.GlideHelper
import kotlinx.android.synthetic.main.adapter_category.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CategoryAdapter(val context: Context, var category: ArrayList<DataCategory>, val clickListener: (DataCategory, Int) -> Unit):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bing(category: DataCategory){
            view.tv_category.text = category.category
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_category, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(category[position])
        GlideHelper.setImage(context, "http://192.168.1.8/penjualan/public/storage" + category[position].image!!, holder.view.riv_image)
        holder.view.rl_category.setOnClickListener {
            Constant.CATEGORY_ID = category[position].id!!
            clickListener(category[position], position)
        }
    }

    override fun getItemCount() = category.size

    fun setData(newCategory: List<DataCategory>) {
        category.clear()
        category.addAll(newCategory)
        notifyDataSetChanged()
    }
}