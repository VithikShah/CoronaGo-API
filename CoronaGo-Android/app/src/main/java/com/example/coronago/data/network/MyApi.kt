package com.example.coronago.data.network

import com.example.coronago.data.network.responses.*
import com.example.coronago.utils.BASE_URL
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {


    //SignInFace api call
    @Multipart
    @POST("login")
    suspend fun userSignInFace(
        @Part image: MultipartBody.Part?
    ): Response<User>

    //PaymentSetup api call
    @Multipart
    @POST("payment")
    suspend fun amountTransaction(
        @Part image: MultipartBody.Part?,
        @Part("publickey") public_key: String?,
        @Part("privatekey") private_key: String?,
        @Part("amount") public: Float?
    ): Response<PaymentSetup>

    //Balance Fetch api call
    @Multipart
    @POST("balance")
    suspend fun balance(
        @Part("publickey") public_key: String?
    ): Response<Balance>

    //AddInfo api call
    @Multipart
    @POST("addinfo")
    suspend fun addInfo(
        @Part image: MultipartBody.Part?,
        @Part("medicines") medicines: String?,
        @Part("status") status: Int?,
        @Part("type") type: Int?
    ): Response<AddInfo>

    //Getinfo api call
    @Multipart
    @POST("getinfo")
    suspend fun getInfo(
        @Part image: MultipartBody.Part?,
        @Part("type") type: Int?
    ): Response<GetInfo>

    //Getinfo api call
    @Multipart
    @POST("getinfo")
    suspend fun getInfo0(
        @Part image: MultipartBody.Part?,
        @Part("type") type: Int?
    ): Response<GetInfo0>


    //SignInPassword api call
    @Multipart
    @POST("emaillogin")
    suspend fun userSignInPassword(
        @Part("email") email: String?,
        @Part("password") password: String?
    ): Response<EmailUser>


    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi {
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}