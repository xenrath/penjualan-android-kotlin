package com.xenrath.penjualan.ui.product

import com.xenrath.penjualan.data.model.product.ResponseCategoryList
import com.xenrath.penjualan.data.model.product.ResponseProductList

interface ProductContract {

    interface Presenter {
        fun getCategory()
        fun getProduct(category_id: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultCategory(responseCategoryList: ResponseCategoryList)
        fun onResultProduct(responseProductList: ResponseProductList)
    }

}