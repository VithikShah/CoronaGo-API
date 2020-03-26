package com.example.coronago.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronago.data.repositories.InfoRepository


@Suppress("UNCHECKED_CAST")
class Scan1ViewModelFactory(
    private val repository: InfoRepository,
    private val activity: Activity,
    val type: Int

) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Scan1ViewModel(repository, activity, type) as T
    }
}