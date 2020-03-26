package com.example.coronago.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronago.data.repositories.InfoRepository
import com.example.coronago.data.repositories.UserRepository


@Suppress("UNCHECKED_CAST")
class SignInFaceViewModelFactory(
    private val repository: UserRepository,
    private val activity: Activity
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignInFaceViewModel(repository, activity) as T
    }
}