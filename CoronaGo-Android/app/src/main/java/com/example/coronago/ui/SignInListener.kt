package com.example.coronago.ui

import com.example.coronago.data.network.responses.EmailUser
import com.example.coronago.data.network.responses.User

interface SignInListener {

    fun onStarted()
    fun onSuccessPassword(user: EmailUser)
    fun onFaliure(message: String)
}