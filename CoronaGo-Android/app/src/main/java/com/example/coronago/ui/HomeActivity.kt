package com.example.coronago.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.coronago.R
import com.example.coronago.data.network.CoronaApi
import com.example.coronago.data.network.MyApi
import com.example.coronago.data.network.NetworkConnectionInterceptor
import com.example.coronago.data.network.responses.PaymentSetup
import com.example.coronago.data.repositories.CoronaRepository
import com.example.coronago.data.repositories.PaymentRepository
import com.example.coronago.databinding.ActivityHomeBinding
import com.example.coronago.utils.ApiException
import com.example.coronago.utils.Coroutines
import com.example.coronago.utils.NoInternetExcepetion
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    var binding: ActivityHomeBinding? = null
    var sharedPref3: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref3 = getSharedPreferences("public_key", 0)
        val sharedPref2: SharedPreferences = getSharedPreferences("type", 0)
        val sharedPref: SharedPreferences = getSharedPreferences("signed_in", 0)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = CoronaApi(networkConnectionInterceptor)
        val repository = CoronaRepository(api)
        val networkConnectionInterceptor2 = NetworkConnectionInterceptor(this)
        val api2 = MyApi(networkConnectionInterceptor2)
        val repository2 = PaymentRepository(api2)

        val factory = HomeViewModelFactory(repository, this)
        val viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
//        viewModel.getIndia()
//        viewModel.getGlobal()
//
//        binding!!.homeViewModel = viewModel
//
//        viewModel.india.observe(this, Observer { indiaData ->
//            india_data.also {
//                it.text = indiaData
//            }
//        })
//        viewModel.global.observe(this, Observer { globalData ->
//            global_data.also {
//                it.text = globalData
//            }
//        })

        binding!!.globaldata.text = "Cases: 471820, Deaths: 21297, Recovered: 114703"
        binding!!.indiadata.text = "Cases: 674, Deaths: 13, Recovered: 43"
        binding!!.walletButton.setOnClickListener {
            val intent = Intent(this, PaymentSetupActivity::class.java)
            startActivity(intent)
        }
        binding!!.logoutButton.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putBoolean("signed_in", false)
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)

        }

        if (sharedPref2.getInt("type", 0) == 0) {
            binding!!.scanButton.setOnClickListener {
                val intent = Intent(this, Scan0Activity::class.java)
                startActivity(intent)
            }
        } else {

            binding!!.scanButton.setOnClickListener {
                val intent = Intent(this, Scan1Activity::class.java)
                startActivity(intent)
            }
        }
        if (sharedPref2.getInt("type", 0) == 2) {
            binding!!.addInfoButton.visibility = View.VISIBLE
            binding!!.addInfoButton.setOnClickListener {
                val intent = Intent(this, AddInfoActivity::class.java)
                startActivity(intent)
            }
        }


        val public_key: String? = sharedPref3!!.getString("public_key", "")
        binding!!.balance.text = ("Balance: 1000")


        Coroutines.main {
            val unit = try {
                val imageResponse =
                    repository.getAllCases()
                Log.v("after", "main activity get data live")
                imageResponse.let {
                    binding!!.globaldata.text =
                        "Cases: " + it!!.cases.toString() + " Deaths: " + it!!.deaths.toString() + " Recovered: " + it!!.recovered.toString()
                    return@main
                }
            } catch (e: ApiException) {
                Log.v("apiexcept", "test1")

            } catch (e: NoInternetExcepetion) {
                Log.v("internet except", "test1")

            }
            val unit2 = try {
                val imageResponse =
                    repository.getIndiaCases()
                Log.v("after", "main activity get data live")
                imageResponse.let {
                    binding!!.indiadata.text =
                        "Cases: " + it!!.cases.toString() + " Deaths: " + it!!.deaths.toString() + " Recovered: " + it!!.recovered.toString()
                    return@main
                }
            } catch (e: ApiException) {
                Log.v("apiexcept", "test1")

            } catch (e: NoInternetExcepetion) {
                Log.v("internet except", "test1")

            }

            val unit3 = try {
                val imageResponse =
                    repository2.balance(public_key!!)
                Log.v("after", "main activity get data live")
                imageResponse.let {
                    binding!!.balance.text = ("Balance: 1000")
                        return@main
                }
            } catch (e: ApiException) {
                Log.v("apiexcept", "test1")

            } catch (e: NoInternetExcepetion) {
                Log.v("internet except", "test1")

            }
        }

    }
}
