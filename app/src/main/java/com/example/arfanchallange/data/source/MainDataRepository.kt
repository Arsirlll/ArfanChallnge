package com.example.arfanchallange.data.source

import androidx.lifecycle.MutableLiveData
import com.example.arfanchallange.data.MainData
import com.example.arfanchallange.data.RepoData
import com.example.arfanchallange.data.source.local.MainDataLocalSource

class MainDataRepository(
    val remoteDataSource: MainDataSource,
    val localDataSource: MainDataSource
) : MainDataSource {
    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {
        remoteDataSource.getMainData(object : MainDataSource.GetMainDataCallback {
            override fun onDataLoaded(mainData: MainData?) {
                callback.onDataLoaded(mainData)
            }

            override fun onNotAvailable() {
                callback.onNotAvailable()
            }

            override fun onError(msg: String?) {
                callback.onError(msg)
            }

        })
    }

    override fun getRepoData(callback: MainDataSource.GetRepoDataCallback) {
        localDataSource.getRepoData(object : MainDataSource.GetRepoDataCallback {
            override fun onDataLoaded(repoData: MutableList<RepoData?>) {
                callback.onDataLoaded(repoData)
            }

            override fun onNotAvailable() {
                callback.onNotAvailable()
            }

            override fun onError(msg: String?) {
                callback.onError(msg)
            }

        })
    }

    companion object {
        private var INSTANCE: MainDataRepository? = null

        @JvmStatic
        fun getInstance(
            mainDataRemoteSource: MainDataSource,
            newsLocalDataSource: MainDataLocalSource
        ) =
            INSTANCE ?: synchronized(MainDataRepository::class.java) {
                INSTANCE ?: MainDataRepository(mainDataRemoteSource, mainDataRemoteSource)
                    .also { INSTANCE = it }

            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}