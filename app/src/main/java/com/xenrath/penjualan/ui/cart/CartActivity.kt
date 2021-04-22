package com.xenrath.penjualan.ui.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.Constant
import com.xenrath.penjualan.data.database.PrefManager
import com.xenrath.penjualan.data.model.cart.DataCart
import com.xenrath.penjualan.data.model.cart.ResponseCartList
import com.xenrath.penjualan.data.model.cart.ResponseCartUpdate
import com.xenrath.penjualan.data.model.cart.ResponseCheckout
import com.xenrath.penjualan.ui.agent.search.AgentSearchActivity
import com.xenrath.penjualan.ui.cart.add.CartAddActivity
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity(), CartContract.View {

    private lateinit var prefManager: PrefManager
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartPresenter: CartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        prefManager = PrefManager(this)
        cartPresenter = CartPresenter(this)
        cartPresenter.getCart(prefManager.prefUsername)
    }

    override fun onStart() {
        super.onStart()
        if (Constant.IS_CHANGED){
            Constant.IS_CHANGED = false
            cartPresenter.getCart(prefManager.prefUsername)
            et_agent.setText(Constant.AGENT_NAME)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.AGENT_NAME = ""
    }

    override fun initActivity() {
        supportActionBar!!.title = "Keranjang"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        cartAdapter = CartAdapter(this, arrayListOf())
        {
            dataCart: DataCart, position: Int ->
            cartPresenter.deleteItemCart(dataCart.id!!)
        }
    }

    override fun initListener() {
        tv_reset.visibility = View.GONE
        et_agent.visibility = View.GONE

        rv_cart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }

        swipe.setOnRefreshListener {
            cartPresenter.getCart(prefManager.prefUsername)
        }

        btn_add.setOnClickListener {
            startActivity(Intent(this, CartAddActivity::class.java))
        }

        tv_reset.setOnClickListener {
            showDialog()
        }

        et_agent.setOnClickListener {
            startActivity(Intent(this, AgentSearchActivity::class.java))
        }

        btn_checkout.setOnClickListener {
            if (cartAdapter.cart.isNullOrEmpty()){
                showMesage("Keranjang kosong")
            } else {
                if (et_agent.text.isNullOrEmpty()){
                    et_agent.error = "Tidak boleh kosong"
                } else {
                    cartPresenter.checkOut(prefManager.prefUsername, Constant.AGENT_ID)
                }
            }
        }
    }

    override fun onLoadingCart(loading: Boolean) {
        when(loading){
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultCart(responseCartList: ResponseCartList) {
        val dataCart: List<DataCart> = responseCartList.dataCart
        if (dataCart.isNullOrEmpty()){
            tv_reset.visibility = View.GONE
            et_agent.visibility = View.GONE
        } else {
            cartAdapter.setData(dataCart)
            tv_reset.visibility = View.VISIBLE
            et_agent.visibility = View.VISIBLE
        }
    }

    override fun showMesage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResultDelete(responseCartUpdate: ResponseCartUpdate) {
        cartPresenter.getCart(prefManager.prefUsername)
        cartAdapter.removeAll()
    }

    override fun showDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Konfirmasi")
        dialog.setMessage("Hapus semua item dalam keranjang?")
        dialog.setPositiveButton("Hapus"){ dialog, which ->
            cartPresenter.deleteCart(prefManager.prefUsername)
            dialog.dismiss()
        }
        dialog.setNegativeButton("Batal"){ dialog, which ->
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onLoadingCheckout(loading: Boolean) {
        when(loading){
            true -> {
                btn_checkout.isEnabled = false
                btn_checkout.setText("Memuat...")
            }
            false -> {
                btn_checkout.isEnabled = true
                btn_checkout.setText("Checkout")
            }
        }
    }

    override fun onResultCheckout(responseCheckout: ResponseCheckout) {
        cartPresenter.getCart(prefManager.prefUsername)
        cartAdapter.removeAll()
    }
}