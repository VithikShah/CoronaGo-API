package com.example.coronago.data.repositories

import com.example.coronago.data.network.MyApi
import com.example.coronago.data.network.SafeApiRequest
import com.example.coronago.data.network.responses.EmailUser
import com.example.coronago.data.network.responses.User
import okhttp3.MultipartBody

class UserRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun userSignInFace(
        image: MultipartBody.Part
    ): User {
        return apiRequest { api.userSignInFace(image) }
    }

    suspend fun userSignInPassword(
        email: String,
        password: String
    ): EmailUser {
        return apiRequest { api.userSignInPassword(email, password) }
    }
}