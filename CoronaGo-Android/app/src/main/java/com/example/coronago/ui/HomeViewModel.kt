package com.example.coronago.ui

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coronago.data.repositories.CoronaRepository
import com.example.coronago.utils.Coroutines
import kotlinx.coroutines.Job

class HomeViewModel(
    val repository: CoronaRepository,
    private val activity: Activity
) : ViewModel(
) {
    private lateinit var job: Job

    private val _india = MutableLiveData<String>()
    private val _global = MutableLiveData<String>()

    val india: LiveData<String>
        get() = _india

    val global: LiveData<String>
        get() = _global


    fun getIndia() {
        job = Coroutines.ioThenMain(
            { repository.getIndiaCases() },
            {
                _india.value =
        "cases"
//                    "Cases: " + it!!.cases.toString() + " Deaths: " + it!!.deaths.toString() + " Recovered: " + it!!.recovered.toString()
            }
        )
    }

    fun getGlobal() {
        job = Coroutines.ioThenMain(
            { repository.getAllCases() },
            {
                _global.value ="cases"
//                    "Cases: " + it!!.cases.toString() + " Deaths: " + it!!.deaths.toString() + " Recovered: " + it!!.recovered.toString()
            }
        )

    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
