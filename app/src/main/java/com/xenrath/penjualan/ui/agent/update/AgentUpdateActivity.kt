package com.xenrath.penjualan.ui.agent.update

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xenrath.penjualan.R
import com.xenrath.penjualan.data.Constant
import com.xenrath.penjualan.data.model.agent.ResponseAgentDetail
import com.xenrath.penjualan.data.model.agent.ResponseAgentUpdate
import com.xenrath.penjualan.ui.agent.AgentMapsActivity
import com.xenrath.penjualan.util.FileUtils
import com.xenrath.penjualan.util.GalleryHelper
import com.xenrath.penjualan.util.GlideHelper
import kotlinx.android.synthetic.main.activity_agent_create.*

class AgentUpdateActivity : AppCompatActivity(), AgentUpdateContract.View {

    private var uriImage: Uri? = null
    private var pickImage = 1

    lateinit var presenter: AgentUpdatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_create)
        presenter = AgentUpdatePresenter(this)
        presenter.getDetail(Constant.AGENT_ID)
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
        supportActionBar!!.title = "Ubah Agen"
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
                address.isNullOrEmpty() || location.isNullOrEmpty()
            ) {
                showMessage("Lengkapi data dengan benar")
            } else {
                presenter.updateAgent(
                    Constant.AGENT_ID,
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

    override fun onResultDetail(responseAgentDetail: ResponseAgentDetail) {
        val agent = responseAgentDetail.dataAgent
        et_store.setText(agent.store)
        et_owner.setText(agent.owner)
        et_address.setText(agent.address)
        et_location.setText("${agent.latitude}, ${agent.longitude}")

        Constant.LATITUDE = agent.latitude!!
        Constant.LONGITUDE = agent.longitude!!

        GlideHelper.setImage(this, "http://192.168.1.7/penjualan/public/storage/uploads/agent/" + agent.image!!, iv_image)
    }

    override fun onResultUpdate(responseAgentUpdate: ResponseAgentUpdate) {
        showMessage(responseAgentUpdate.message)
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}