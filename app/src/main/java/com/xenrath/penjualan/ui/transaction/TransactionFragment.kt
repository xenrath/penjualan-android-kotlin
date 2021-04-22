package com.xenrath.penjualan.ui.transaction

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.Constant
import com.xenrath.penjualan.data.database.PrefManager
import com.xenrath.penjualan.data.model.transaction.DataTransaction
import com.xenrath.penjualan.data.model.transaction.ResponseTransactionList
import com.xenrath.penjualan.ui.cart.CartActivity
import com.xenrath.penjualan.ui.cart.CartAdapter
import com.xenrath.penjualan.ui.transaction.detail.TransactionDetailFragment
import kotlinx.android.synthetic.main.fragment_transaction.*
import kotlinx.android.synthetic.main.fragment_transaction.view.*

class TransactionFragment : Fragment(), TransactionContract.View {

    lateinit var prefManager: PrefManager
    lateinit var transactionAdapter: TransactionAdapter
    lateinit var presenter: TransactionPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transaction, container, false)

        prefManager = PrefManager(requireContext())
        presenter = TransactionPresenter(this)
        initListener(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Transaksi"
        presenter.getTransaction(prefManager.prefUsername)
    }

    override fun initFragment() {
        transactionAdapter = TransactionAdapter(requireContext(), arrayListOf()){
            dataTransaction, position ->
            onClickTransaction(dataTransaction.invoice_number!!)
        }
    }

    override fun initListener(view: View) {
        val rvTransaction = view.rv_transaction
        val swipe = view.swipe
        val fab = view.fab

        rvTransaction!!.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }

        swipe.setOnRefreshListener {
            presenter.getTransaction(prefManager.prefUsername)
        }

        fab.setOnClickListener {
            requireContext().startActivity(Intent(context, CartActivity::class.java))
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResult(responseTransactionList: ResponseTransactionList) {
        val dataTransaction: List<DataTransaction> = responseTransactionList.dataTransaction
        transactionAdapter.setData(dataTransaction)
    }

    override fun onClickTransaction(invoice: String) {
        Constant.INVOICE = invoice
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, TransactionDetailFragment(), "transDetail")
            .commit()
    }
}