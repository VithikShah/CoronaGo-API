package com.example.coronago.ui

import com.example.coronago.data.network.responses.AddInfo
import com.example.coronago.data.network.responses.PaymentSetup


interface AddInfoListener {

    fun onStarted()
    fun onFailure(message: String)
    fun onSuccessInfo(addInfo: AddInfo)
}