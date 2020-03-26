package com.example.coronago.ui

import com.example.coronago.data.network.responses.GetInfo0

interface Scan0Listener {
    fun onStarted()
    fun onFailure(message: String)
    fun onSuccessInfo(getInfo0: GetInfo0)
}