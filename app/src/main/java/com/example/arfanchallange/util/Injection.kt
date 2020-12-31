package com.example.arfanchallange.util

import android.content.Context
import android.preference.PreferenceManager
import com.example.arfanchallange.data.source.MainDataRepository
import com.example.arfanchallange.data.source.local.MainDataLocalSource
import com.example.arfanchallange.data.source.remote.MainDataRemoteSource

object Injection {

    fun providerMainDataRepository(context: Context) = MainDataRepository.getInstance(MainDataRemoteSource,
    MainDataLocalSource.getInstance(PreferenceManager.getDefaultSharedPreferences(context))!!)
}