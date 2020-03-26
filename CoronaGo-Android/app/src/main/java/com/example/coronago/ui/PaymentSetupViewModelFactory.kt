package com.example.coronago.ui

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronago.data.repositories.PaymentRepository

@Suppress("UNCHECKED_CAST")
class PaymentSetupViewModelFactory(
    private val repository: PaymentRepository,
    private val activity: Activity,
    private val public_key: String,
    private val private_key: String

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PaymentSetupViewModel(repository, activity, public_key, private_key) as T
    }

}