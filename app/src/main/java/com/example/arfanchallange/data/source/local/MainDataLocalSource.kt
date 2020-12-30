package com.example.arfanchallange.data.source.local

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.example.arfanchallange.data.source.MainDataSource

class MainDataLocalSource  private constructor(private val preferences: SharedPreferences) : MainDataSource{

    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {
    }

    override fun getRepoData(callback: MainDataSource.GetRepoDataCallback) {
    }

    companion object {
        private var INSTANCE: MainDataLocalSource? = null
        @JvmStatic
        fun getInstance(preferences: SharedPreferences) : MainDataLocalSource?{
            if (INSTANCE == null){
                synchronized(MainDataLocalSource::class.java){
                    INSTANCE = MainDataLocalSource(preferences)
                }
            }
            return INSTANCE
        }

        @VisibleForTesting
        fun clearInstance(){
            INSTANCE = null
        }
    }
}