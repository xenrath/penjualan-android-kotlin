package com.xenrath.penjualan.ui.agent.create

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.Constant
import com.xenrath.penjualan.data.model.agent.ResponseAgentUpdate
import com.xenrath.penjualan.ui.agent.AgentMapsActivity
import com.xenrath.penjualan.util.FileUtils
import com.xenrath.penjualan.util.GalleryHelper
import kotlinx.android.synthetic.main.activity_agent_create.*
import kotlinx.android.synthetic.main.activity_agent_create.progress
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_user.*

class AgentCreateActivity : AppCompatActivity(), AgentCreateContract.View {

    private var uriImage: Uri? = null
    private var pickImage = 1

    lateinit var presenter: AgentCreatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_create)
        presenter = AgentCreatePresenter(this)
    }

    override fun onStart() {
        super.onStart()
        if (Constant.LATITUDE.isNotEmpty()){
            et_location.setText("${Constant.LATITUDE}, ${Constant.LONGITUDE}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.LATITUDE = ""
        Constant.LONGITUDE = ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == Activity.RESULT_OK){
            uriImage = data!!.data
            iv_image.setImageURI(uriImage)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun initActivity() {
        supportActionBar!!.title = "Agen Baru"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        et_location.setOnClickListener {
            startActivity(Intent(this, AgentMapsActivity::class.java))
        }

        iv_image.setOnClickListener {
            if (GalleryHelper.permissionGallery(this, this, pickImage)){
                GalleryHelper.openGallery(this)
            }
        }

        btn_save.setOnClickListener{
            val store = et_store.text
            val owner = et_owner.text
            val address = et_address.text
            val location = et_location.text
            if (
                store.isNullOrEmpty() || owner.isNullOrEmpty() ||
                address.isNullOrEmpty() || location.isNullOrEmpty() ||
                uriImage == null
            ) {
                showMessage("Lengkapi data dengan benar")
            } else {
                presenter.insertAgent(
                    store.toString(),
                    owner.toString(),
                    address.toString(),
                    Constant.LATITUDE,
                    Constant.LONGITUDE,
                    FileUtils.getFile(this, uriImage)
                )
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btn_save.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btn_save.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseAgentUpdate: ResponseAgentUpdate) {
        showMessage(responseAgentUpdate.message)
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}