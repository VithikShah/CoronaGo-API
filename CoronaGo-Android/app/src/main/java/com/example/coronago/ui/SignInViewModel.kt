package com.example.coronago.ui

import android.app.Activity
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.coronago.data.repositories.UserRepository
import com.example.coronago.utils.ApiException
import com.example.coronago.utils.Coroutines
import com.example.coronago.utils.NoInternetExcepetion

class SignInViewModel(
    val repository: UserRepository,
     val activity: Activity?
) : ViewModel() {

    var email: String? = null
    var password: String? = null

    var signInListener: SignInListener? = null

    fun onSignInPasswordClick(view: View) {
        signInListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            signInListener?.onFaliure("Invalid email or password")
            return
        }
        Coroutines.main {
            val unit = try {
                val authResponse = repository.userSignInPassword(email!!, password!!)
                authResponse.let {
                    signInListener?.onSuccessPassword(it)
                    return@main
                }

            } catch (e: ApiException) {
                signInListener?.onFaliure(e.message!!)
            } catch (e: NoInternetExcepetion) {
                signInListener?.onFaliure(e.message!!)

            }

        }
    }

}
