package com.example.coronago.data.network.responses

data class AllCaseResponse(
    var cases: Long? = 0,
    var deaths: Long? = 0,
    var recovered: Long? = 0
)