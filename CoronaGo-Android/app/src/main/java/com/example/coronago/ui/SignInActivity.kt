package com.example.coronago.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.coronago.R
import com.example.coronago.data.network.MyApi
import com.example.coronago.data.network.NetworkConnectionInterceptor
import com.example.coronago.data.network.responses.EmailUser
import com.example.coronago.data.repositories.UserRepository
import com.example.coronago.databinding.ActivitySignInBinding
import com.example.coronago.utils.hide
import com.example.coronago.utils.show
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity(), SignInListener {
    var sharedPref: SharedPreferences? = null
    var viewModel: SignInViewModel? = null
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

        val binding: ActivitySignInBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val repository = UserRepository(api)
        val factory = SignInViewModelFactory(repository, this)
        viewModel = ViewModelProviders.of(this, factory).get(SignInViewModel::class.java)

        binding.signinviewmodel = viewModel
        viewModel!!.signInListener = this

        binding.signInFaceButton.setOnClickListener {
            val intent = Intent(this, SignInFaceActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStarted() {
        sign_in_progress_bar.show()
    }

    override fun onFaliure(message: String) {
        sign_in_progress_bar.hide()
    }

    override fun onSuccessPassword(user: EmailUser) {
        sign_in_progress_bar.hide()
        Log.v("login pass :", user.name.toString()  + user.type )
        Toast.makeText(this, "Welcome, "+ user.name.toString(), Toast.LENGTH_SHORT).show()

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


}


