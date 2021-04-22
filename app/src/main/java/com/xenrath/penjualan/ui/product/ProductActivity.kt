package com.xenrath.penjualan.ui.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.model.product.DataCategory
import com.xenrath.penjualan.data.model.product.DataProduct
import com.xenrath.penjualan.data.model.product.ResponseCategoryList
import com.xenrath.penjualan.data.model.product.ResponseProductList
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var presenter: ProductPresenter
    private var categoryId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        presenter = ProductPresenter(this)
        presenter.getCategory()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        when(View.VISIBLE){
            rv_category.visibility -> finish()
            rv_product.visibility -> {
                rv_product.visibility = View.GONE
                rv_category.visibility = View.VISIBLE
                tv_category.text = "Pilih Kategori"
            }
        }
    }

    override fun initActivity() {
        supportActionBar!!.hide()

        productAdapter = ProductAdapter(this, arrayListOf())

        categoryAdapter = CategoryAdapter(this, arrayListOf()){
            category: DataCategory, position: Int ->
            categoryId = category.id!!
            presenter.getProduct(category.id)
        }
    }

    override fun initListener() {
        rv_category.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoryAdapter
        }

        rv_product.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productAdapter
        }

        swipe.setOnRefreshListener {
            when (View.VISIBLE){
                rv_category.visibility -> presenter.getCategory()
                rv_product.visibility -> presenter.getProduct(categoryId)
            }
        }

        iv_close.setOnClickListener {
            when(View.VISIBLE){
                rv_category.visibility -> finish()
                rv_product.visibility -> {
                    rv_product.visibility = View.GONE
                    rv_category.visibility = View.VISIBLE
                    tv_category.text = "Pilih Kategori"
                }
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true -> {
                swipe.isRefreshing = true
                rv_category.visibility = View.GONE
                rv_product.visibility = View.GONE
            }
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultCategory(responseCategoryList: ResponseCategoryList) {
        rv_category.visibility = View.VISIBLE
        val dataCategory: List<DataCategory> = responseCategoryList.dataCategory
        categoryAdapter.setData(dataCategory)
        tv_category.text = "Pilih Kategori"
    }

    override fun onResultProduct(responseProductList: ResponseProductList) {
        rv_product.visibility = View.VISIBLE
        val dataProduct: List<DataProduct> = responseProductList.dataProduct
        productAdapter.setData(dataProduct)
        tv_category.text = dataProduct[0].category
    }
}