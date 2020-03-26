package com.example.coronago.data.repositories

import com.example.coronago.data.network.MyApi
import com.example.coronago.data.network.SafeApiRequest
import com.example.coronago.data.network.responses.AddInfo
import com.example.coronago.data.network.responses.GetInfo
import com.example.coronago.data.network.responses.GetInfo0
import okhttp3.MultipartBody

class InfoRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun addInfo(
        image: MultipartBody.Part,
        medicines: String?,
        status: Int?,
        type: Int?
    ): AddInfo {
        return apiRequest { api.addInfo(image, medicines, status, type) }
    }

    suspend fun getInfo(
        image: MultipartBody.Part,
        type: Int
    ): GetInfo {
        return apiRequest { api.getInfo(image, type) }
    }

    suspend fun getInfo0(
        image: MultipartBody.Part,
        type: Int
    ): GetInfo0 {
        return apiRequest { api.getInfo0(image, type) }
    }

}
