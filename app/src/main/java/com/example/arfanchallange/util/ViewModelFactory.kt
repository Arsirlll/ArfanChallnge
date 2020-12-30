package com.example.arfanchallange.util

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arfanchallange.data.source.MainDataRepository
import com.example.arfanchallange.main.MainViewModel
import com.example.arfanchallange.repo.RepoViewModel

class ViewModelFactory private constructor(
    private val application: Application,
    private val mainDataRepository: MainDataRepository
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>)= with(modelClass) {
        when{
            isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(application,mainDataRepository)
            isAssignableFrom(RepoViewModel::class.java) ->
                RepoViewModel(application, mainDataRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java){
                    INSTANCE
                        ?: ViewModelFactory(
                            application, Injection.providedMainDataRepository(application.applicationContext))
                            .also { INSTANCE = it }
                }

        @VisibleForTesting
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}