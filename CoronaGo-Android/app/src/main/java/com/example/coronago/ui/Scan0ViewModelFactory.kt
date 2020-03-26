package com.example.coronago.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronago.data.repositories.InfoRepository


@Suppress("UNCHECKED_CAST")
class Scan0ViewModelFactory(
    private val repository: InfoRepository,
    private val activity: Activity

) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Scan0ViewModel(repository, activity) as T
    }
}