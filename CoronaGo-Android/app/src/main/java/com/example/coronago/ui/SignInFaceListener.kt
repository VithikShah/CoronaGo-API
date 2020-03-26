package com.example.coronago.ui

import com.example.coronago.data.network.responses.GetInfo0
import com.example.coronago.data.network.responses.User

interface SignInFaceListener {
    fun onStarted()
    fun onFailure(message: String)
    fun onSuccessInfo(user: User)
}