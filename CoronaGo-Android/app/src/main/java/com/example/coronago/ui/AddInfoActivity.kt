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
import com.example.coronago.data.network.responses.AddInfo
import com.example.coronago.data.repositories.InfoRepository
import com.example.coronago.databinding.ActivityAddInfoBinding
import com.example.coronago.utils.hide
import com.example.coronago.utils.show
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_add_info.*

class AddInfoActivity : AppCompatActivity(), GetInfoSetupClickListener, AddInfoListener {
    var viewModel: AddInfoViewModel? = null
    var sharedPref2: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref2 = getSharedPreferences("type", 0)

        val type: Int? = sharedPref2!!.getInt("type", 0)

        val binding: ActivityAddInfoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_info)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val repository = InfoRepository(api)
        val factory = AddInfoViewModelFactory(repository, this, type!!)
        viewModel = ViewModelProviders.of(this, factory).get(AddInfoViewModel::class.java)

        binding.addInfoViewModel = viewModel
        viewModel!!.addInfoListener = this
        viewModel!!.onCameraClickListener = this
    }


    override fun onStarted() {
        progress_bar.show()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
    }

    override fun onSuccessInfo(addInfo: AddInfo) {
        progress_bar.hide()
        Log.v("success addinfo", addInfo.status.toString())
        Intent(this, HomeActivity::class.java).also {
            startActivity(it)
        }
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
