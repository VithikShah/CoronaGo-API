package com.example.coronago.data.network.responses

data class CountryCaseResponse(
    var country: String? = null,
    var countryInfo: CountryInfo? = null,
    var cases: Int? = 0,
    var todayCases: Int? = 0,
    var deaths: Int? = 0,
    var todayDeaths: Int? = 0,
    var recovered: Int? = 0,
    var active: Int? = 0,
    var critical: Int? = 0,
    var casesPerOneMillion: Int? = 0,
    var deathsPerOneMillion: Int? = 0
)

