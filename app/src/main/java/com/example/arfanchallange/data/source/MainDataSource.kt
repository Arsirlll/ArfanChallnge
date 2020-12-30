package com.example.arfanchallange.data.source

import androidx.lifecycle.MutableLiveData
import com.example.arfanchallange.data.MainData
import com.example.arfanchallange.data.RepoData

interface MainDataSource {
    fun getMainData(callback: GetMainDataCallBack)
    fun getRepoData(callback: GetRepoDataCallBack)

    interface GetMainDataCallBack {
        fun onDataLoaded(mainData: MainData?)
        fun onNotAvaliable()
        fun onError(msg: String?)
    }

    interface GetRepoDataCallBack {
        fun onDataloaded(repoData: MutableLiveData<RepoData?>)
        fun onNotAvailable()
        fun onError(msg: String?)
    }
}