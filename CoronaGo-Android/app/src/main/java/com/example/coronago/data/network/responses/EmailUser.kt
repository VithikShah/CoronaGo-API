package com.example.coronago.data.network.responses

data class EmailUser(
    var message: String? = null,
    var name: String? = null,
    var privatekey: String? = null,
    var publickey: String? = null,
    var type: Int? = 0
)
