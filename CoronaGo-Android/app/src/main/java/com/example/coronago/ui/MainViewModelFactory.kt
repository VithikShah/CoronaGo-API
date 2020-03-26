package com.example.coronago.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronago.data.repositories.CoronaRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val repository: CoronaRepository,
    private val activity: Activity

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository, activity) as T
    }

}