package com.example.coronago.ui

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.coronago.R
import com.example.coronago.data.network.CoronaApi
import com.example.coronago.data.network.NetworkConnectionInterceptor
import com.example.coronago.data.repositories.CoronaRepository
import com.example.coronago.databinding.ActivityMainBinding
import com.example.coronago.utils.ApiException
import com.example.coronago.utils.Coroutines
import com.example.coronago.utils.NoInternetExcepetion
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref: SharedPreferences = getSharedPreferences("signed_in", 0)

        if (sharedPref.getBoolean("signed_in", false)) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
           var binding:ActivityMainBinding  = DataBindingUtil.setContentView(this, R.layout.activity_main)

            val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
            val api = CoronaApi(networkConnectionInterceptor)
            val repository = CoronaRepository(api)
            val factory = MainViewModelFactory(repository, this)
            val viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)





//            viewModel.getIndia()
//            viewModel.getGlobal()
//            Log.v("test1","test1")
            binding!!.mainViewModel = viewModel
//
//            viewModel.india.observe(this, Observer { indiaData ->
//                indiadata.also {
//                    it.text = indiaData
//                }
//            })
//            Log.v("test2","test2")
//
//            viewModel.global.observe(this, Observer { globalData ->
//                globaldata.also {
//                    it.text = globalData
//                }
//            })
            Log.v("test3","test1")

            binding!!.indiadata.text = "Cases: 674, Deaths: 13, Recovered: 43"
            binding!!.globaldata.text = "Cases: 471820, Deaths: 21297, Recovered: 114703"
            binding!!.continueButton.setOnClickListener {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
            Log.v("test14","test1")


            binding!!.scanButton.setOnClickListener {
                val intent = Intent(this, Scan0Activity::class.java)
                startActivity(intent)
            }

            Coroutines.main {
                val unit = try {
                    val imageResponse =
                        repository.getAllCases()
                    Log.v("after", "main activity get data live")
                    imageResponse.let {
                        binding!!.globaldata.text = "Cases: " + it!!.cases.toString() + " Deaths: " + it!!.deaths.toString() + " Recovered: " + it!!.recovered.toString()
                        return@main
                    }
                } catch (e: ApiException) {
                    Log.v("apiexcept","test1")

                } catch (e: NoInternetExcepetion) {
                    Log.v("internet except","test1")

                }
                val unit2 = try {
                    val imageResponse =
                        repository.getIndiaCases()
                    Log.v("after", "main activity get data live")
                    imageResponse.let {
                        binding!!.indiadata.text = "Cases: " + it!!.cases.toString() + " Deaths: " + it!!.deaths.toString() + " Recovered: " + it!!.recovered.toString()
                        return@main
                    }
                } catch (e: ApiException) {
                    Log.v("apiexcept","test1")

                } catch (e: NoInternetExcepetion) {
                    Log.v("internet except","test1")

                }
            }


        }

    }


}
