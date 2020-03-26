package com.example.coronago.data.repositories

import com.example.coronago.data.network.MyApi
import com.example.coronago.data.network.SafeApiRequest
import com.example.coronago.data.network.responses.Balance
import com.example.coronago.data.network.responses.PaymentSetup
import okhttp3.MultipartBody

class PaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun amountTransaction(
        image: MultipartBody.Part,
        public_key: String,
        private_key: String,
        amount: Float
    ): PaymentSetup {
        return apiRequest { api.amountTransaction(image, public_key, private_key, amount) }
    }

    suspend fun balance(
        public_key: String
    ): Balance {
        return apiRequest { api.balance(public_key) }
    }

}