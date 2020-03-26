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
import com.example.coronago.data.network.responses.GetInfo0
import com.example.coronago.data.network.responses.PaymentSetup
import com.example.coronago.data.repositories.InfoRepository
import com.example.coronago.data.repositories.PaymentRepository
import com.example.coronago.databinding.ActivityPaymentSetupBinding
import com.example.coronago.databinding.ActivityScan0Binding
import com.example.coronago.utils.hide
import com.example.coronago.utils.show
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_payment_setup.*

class PaymentSetupActivity : AppCompatActivity(), GetInfoSetupClickListener, PaymentListener {
    var viewModel: PaymentSetupViewModel? = null
    var sharedPref3: SharedPreferences? = null
    var sharedPref4: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref3 = getSharedPreferences("public_key", 0)
        sharedPref4 = getSharedPreferences("private_key", 0)

        val public_key: String? = sharedPref3!!.getString("public_key", "")
        val private_key: String? = sharedPref3!!.getString("private_key", "")

        val binding: ActivityPaymentSetupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_payment_setup)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val repository = PaymentRepository(api)
        val factory = PaymentSetupViewModelFactory(repository, this, public_key!!, private_key!!)
        viewModel = ViewModelProviders.of(this, factory).get(PaymentSetupViewModel::class.java)

        binding.paymentSetupViewModel = viewModel
        viewModel!!.paymentListener = this
        viewModel!!.onCameraClickListener = this
    }


    override fun onStarted() {
        progress_bar.show()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
    }

    override fun onSuccessPaymentSetup(paymentSetup: PaymentSetup) {
        progress_bar.hide()
        Log.v("success get payment : ", paymentSetup.status.toString())
        Intent(this, HomeActivity::class.java).also {
            startActivity(it)
        }
    }

    override fun onCameraButtonClick(view: View) {

        ImagePicker.with(this)
            .cameraOnly()
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
