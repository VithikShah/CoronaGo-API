package com.example.coronago.data.network.responses

data class User(
    var name: String? = null,
    var privatekey: String? = null,
    var publickey: String? = null,
    var type: Int? = 0
)