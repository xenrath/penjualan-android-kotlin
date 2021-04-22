package com.xenrath.penjualan.ui.cart.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.Constant
import com.xenrath.penjualan.data.database.PrefManager
import com.xenrath.penjualan.data.model.cart.ResponseCartUpdate
import com.xenrath.penjualan.ui.product.ProductActivity
import kotlinx.android.synthetic.main.activity_cart_add.*

class CartAddActivity : AppCompatActivity(), CartAddContract.View {

    lateinit var cartAddPresenter: CartAddPresenter
    lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_add)

        prefManager = PrefManager(this)
        cartAddPresenter = CartAddPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        if (Constant.IS_CHANGED){
            Constant.IS_CHANGED = false
            et_product.setText(Constant.PRODUCT_NAME)
            tv_qty.visibility = View.VISIBLE
            np_qty.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.PRODUCT_ID = 0
        Constant.PRODUCT_NAME = ""
        Constant.PRODUCT_QTY = 0
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun initActivity() {
        supportActionBar!!.title = "Tambah Produk"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        tv_qty.visibility = View.GONE
        np_qty.visibility = View.GONE
    }

    override fun initListener() {

        et_product.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }

        np_qty.minValue = 1
        np_qty.maxValue = 25
        np_qty.wrapSelectorWheel = true
        np_qty.setOnValueChangedListener{ picker, oldVal, newVal ->
            Constant.PRODUCT_QTY = newVal.toLong()
        }

        btn_submit.setOnClickListener {
            if (Constant.PRODUCT_ID > 0){
                Constant.PRODUCT_QTY = if (Constant.PRODUCT_QTY > 0) Constant.PRODUCT_QTY else 1
                cartAddPresenter.addCart(
                    prefManager.prefUsername, Constant.PRODUCT_ID, Constant.PRODUCT_QTY
                )
            } else {
                et_product.error = "Tidak boleh kosong"
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true -> {
                progress.visibility = View.VISIBLE
                btn_submit.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btn_submit.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseCartUpdate: ResponseCartUpdate) {
        if (responseCartUpdate.status){
            Constant.IS_CHANGED = true
            finish()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}