package com.example.coronago.ui

import com.example.coronago.data.network.responses.PaymentSetup


interface PaymentListener {

    fun onStarted()
    fun onFailure(message: String)
    fun onSuccessPaymentSetup(paymentSetup: PaymentSetup)
}