package com.example.coronago.utils

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetExcepetion(message: String) : IOException(message)