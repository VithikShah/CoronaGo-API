package com.example.coronago.data.network.responses

data class CountryInfo(
    var iso2: String? = null,
    var iso3: String? = null,
    var id: Int? = 0,
    var lat: Int? = 0,
    var long: Int? = 0,
    var flag: String? = null

)