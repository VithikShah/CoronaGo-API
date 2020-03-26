package com.example.coronago.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.coronago.R
import com.example.coronago.data.network.MyApi
import com.example.coronago.data.network.NetworkConnectionInterceptor
import com.example.coronago.data.network.responses.GetInfo
import com.example.coronago.data.repositories.InfoRepository
import com.example.coronago.databinding.ActivityScan0Binding
import com.example.coronago.databinding.ActivityScan1Binding
import com.example.coronago.utils.hide
import com.example.coronago.utils.show
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_scan1.*

class Scan1Activity : AppCompatActivity(), GetInfoSetupClickListener, Scan1Listener {
    var viewModel: Scan1ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref2: SharedPreferences = getSharedPreferences("type", 0)

        val type: Int = sharedPref2.getInt("type", 0)
        val binding: ActivityScan1Binding =
            DataBindingUtil.setContentView(this, R.layout.activity_scan1)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val repository = InfoRepository(api)
        val factory = Scan1ViewModelFactory(repository, this, type)
        viewModel = ViewModelProviders.of(this, factory).get(Scan1ViewModel::class.java)

        binding.scan1ViewModel = viewModel
        viewModel!!.scan1Listener = this
        viewModel!!.onCameraClickListener = this

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
    }

    override fun onSuccessInfo(getInfo: GetInfo) {
        progress_bar.hide()
        Log.v("succe getinfo 1status:", getInfo.status.toString())
        Intent(this, HomeActivity::class.java).also {
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
