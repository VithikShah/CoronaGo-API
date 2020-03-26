package com.example.coronago.ui

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronago.data.repositories.InfoRepository
import com.example.coronago.data.repositories.PaymentRepository

@Suppress("UNCHECKED_CAST")
class AddInfoViewModelFactory(
    private val repository: InfoRepository,
    private val activity: Activity,
    private val type: Int

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddInfoViewModel(repository, activity, type) as T
    }

}