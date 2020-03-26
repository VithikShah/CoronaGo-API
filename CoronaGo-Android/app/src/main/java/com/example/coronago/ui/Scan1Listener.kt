package com.example.coronago.ui

import com.example.coronago.data.network.responses.GetInfo
import com.example.coronago.data.network.responses.GetInfo0

interface Scan1Listener {
    fun onStarted()
    fun onFailure(message: String)
    fun onSuccessInfo(getInfo: GetInfo)
}