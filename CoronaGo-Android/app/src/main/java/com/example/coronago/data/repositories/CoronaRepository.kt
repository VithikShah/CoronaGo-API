package com.example.coronago.data.repositories

import com.example.coronago.data.network.CoronaApi
import com.example.coronago.data.network.SafeApiRequest
import com.example.coronago.data.network.responses.AllCaseResponse

class CoronaRepository(private val api: CoronaApi) : SafeApiRequest() {
    suspend fun getAllCases() = apiRequest { api.getAllCases() }

    suspend fun getIndiaCases() = apiRequest { api.getIndiaCases() }


}
