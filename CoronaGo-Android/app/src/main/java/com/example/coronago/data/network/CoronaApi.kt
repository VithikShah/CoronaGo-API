package com.example.coronago.data.network

import com.example.coronago.data.network.responses.AllCaseResponse
import com.example.coronago.data.network.responses.CountryCaseResponse
import com.example.coronago.data.network.responses.PaymentSetup
import com.example.coronago.data.network.responses.User
import com.example.coronago.utils.CORONA_URL
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface CoronaApi {

    @GET("all")
    suspend fun getAllCases(): Response<AllCaseResponse>

    @GET("countries/india")
    suspend fun getIndiaCases(): Response<CountryCaseResponse>

    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): CoronaApi {
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(CORONA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CoronaApi::class.java)
        }
    }
}