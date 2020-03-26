package com.example.coronago.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.coronago.R
import com.example.coronago.data.network.MyApi
import com.example.coronago.data.network.NetworkConnectionInterceptor
import com.example.coronago.data.network.responses.GetInfo0
import com.example.coronago.data.repositories.InfoRepository
import com.example.coronago.databinding.ActivityScan0Binding
import com.example.coronago.utils.hide
import com.example.coronago.utils.show
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_scan0.*

class Scan0Activity : AppCompatActivity(), GetInfoSetupClickListener, Scan0Listener {
    var viewModel: Scan0ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityScan0Binding =
            DataBindingUtil.setContentView(this, R.layout.activity_scan0)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val repository = InfoRepository(api)
        val factory = Scan0ViewModelFactory(repository, this)
        viewModel = ViewModelProviders.of(this, factory).get(Scan0ViewModel::class.java)

        binding.scan0ViewModel = viewModel
        viewModel!!.scan0Listener = this
        viewModel!!.onCameraClickListener = this

    }


    override fun onStarted() {
        progress_bar.show()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
    }

    override fun onSuccessInfo(getInfo0: GetInfo0) {
        progress_bar.hide()
        Log.v("success get info status", getInfo0.status.toString())
        if(getInfo0.status == 0) {
            Toast.makeText(this, "Person is Normal", Toast.LENGTH_SHORT).show()
        }
        if(getInfo0.status == 1) {
            Toast.makeText(this, "Person is meant to be Quarantined", Toast.LENGTH_SHORT).show()
        }
        if(getInfo0.status == 2) {
            Toast.makeText(this, "Person is Corona Positive", Toast.LENGTH_SHORT).show()
        }
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }

    override fun onCameraButtonClick(view: View) {

        ImagePicker.with(this)
            .crop(
                1f,
                1f
            )                    //Crop image(Optional), Check Customization for more option
            .compress(150)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                512,
                360
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel?.onActivityResult(requestCode, resultCode, data)
    }


}
