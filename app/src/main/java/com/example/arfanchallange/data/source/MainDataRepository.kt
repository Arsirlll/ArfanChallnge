package com.example.arfanchallange.data.source

import androidx.lifecycle.MutableLiveData
import com.example.arfanchallange.data.MainData
import com.example.arfanchallange.data.RepoData
import com.example.arfanchallange.data.source.local.MainDataLocalSource

class MainDataRepository(
    val remoteDataSource: MainDataSource,
    val localDataSource: MainDataSource
) : MainDataSource {

    override fun getMainData(callback: MainDataSource.GetMainDataCallBack) {
        remoteDataSource.getMainData(object : MainDataSource.GetMainDataCallBack {
            override fun onDataLoaded(mainData: MainData?) {
                callback.onDataLoaded(mainData)
            }

            override fun onNotAvaliable() {
                callback.onNotAvaliable()
            }

            override fun onError(msg: String?) {
                callback.onError(msg)
            }

        })
    }

    override fun getRepoData(callback: MainDataSource.GetRepoDataCallBack) {
        localDataSource.getRepoData(object : MainDataSource.GetRepoDataCallBack {

            override fun onDataloaded(repoData: MutableLiveData<RepoData?>) {
                callback.onDataloaded(repoData)
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