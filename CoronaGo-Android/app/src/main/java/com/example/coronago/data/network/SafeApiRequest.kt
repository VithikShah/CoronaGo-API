package com.example.coronago.data.network

import com.example.coronago.utils.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("error"))
                } catch (e: JSONException) {
                }
                message.append("\t")

            }
            message.append("Error code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}