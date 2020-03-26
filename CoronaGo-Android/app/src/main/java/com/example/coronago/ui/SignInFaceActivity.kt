package com.example.coronago.ui

import android.content.Intent
import android.content.SharedPreferences
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
import com.example.coronago.data.network.responses.GetInfo
import com.example.coronago.data.network.responses.User
import com.example.coronago.data.repositories.InfoRepository
import com.example.coronago.data.repositories.UserRepository
import com.example.coronago.databinding.ActivityScan1Binding
import com.example.coronago.databinding.ActivitySignInBinding
import com.example.coronago.databinding.ActivitySignInFaceBinding
import com.example.coronago.utils.hide
import com.example.coronago.utils.show
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_sign_in_face.*

class SignInFaceActivity : AppCompatActivity(), GetInfoSetupClickListener, SignInFaceListener {
    var viewModel: SignInFaceViewModel? = null
    var sharedPref: SharedPreferences? = null
    var sharedPref2: SharedPreferences? = null
    var sharedPref3: SharedPreferences? = null
    var sharedPref4: SharedPreferences? = null
    var sharedPref5: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("signed_in", 0)
        sharedPref2 = getSharedPreferences("type", 0)
        sharedPref3 = getSharedPreferences("public_key", 0)
        sharedPref4 = getSharedPreferences("private_key", 0)
        sharedPref5 = getSharedPreferences("name", 0)

        val binding: ActivitySignInFaceBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_in_face)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val repository = UserRepository(api)
        val factory = SignInFaceViewModelFactory(repository, this)
        viewModel = ViewModelProviders.of(this, factory).get(SignInFaceViewModel::class.java)

        binding.signInFaceViewModel = viewModel
        viewModel!!.signInFaceListener = this
        viewModel!!.onCameraClickListener = this

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
    }

    override fun onSuccessInfo(user: User) {
        progress_bar.hide()
        Log.v("login with face:", user.name.toString())
        Toast.makeText(this, "Welcome, " + user.name.toString(), Toast.LENGTH_SHORT).show()

        Intent(this, HomeActivity::class.java).also {
            val editor = sharedPref!!.edit()
            editor.putBoolean("signed_in", true)
            editor.apply()

            val editor2 = sharedPref2!!.edit()
            editor2.putInt("type", user.type!!)
            editor2.apply()

            val editor3 = sharedPref3!!.edit()
            editor3.putString("public_key", user.publickey)
            editor3.apply()

            val editor4 = sharedPref4!!.edit()
            editor4.putString("private_key", user.privatekey)
            editor4.apply()

            val editor5 = sharedPref5!!.edit()
            editor5.putString("name", user.name)
            editor5.apply()


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
