package com.example.arfanchallange.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.example.arfanchallange.api.ApiService
import com.example.arfanchallange.api.dao.RepoDataDao
import com.example.arfanchallange.data.MainData
import com.example.arfanchallange.data.RepoData
import com.example.arfanchallange.data.source.MainDataSource
import com.example.arfanchallange.util.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MainDataRemoteSource : MainDataSource {

    private val apiService = ApiService.create()

    @SuppressLint("CheckResult")
    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {
        apiService.getMainData(Constant.username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {

                    if (it.name != "") {
                        val mainData = MainData(
                            it.name,
                            it.location,
                            it.avatar_url,
                            "${it.followers}\nFollowers",
                            "${it.following}\nFollowings",
                            "${it.public_repos}\nRepos"
                        )
                        callback.onDataLoaded(mainData)
                    } else {
                        callback.onNotAvailable()
                    }

                }
            }, {
                callback.onError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun getRepoData(callback: MainDataSource.GetRepoDataCallback) {
        apiService.getReposData(Constant.username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {

                    if (it.isNotEmpty()) {
                        Log.i("xx", " ${it.size}")

                        val listRepo: MutableList<RepoData?> = mutableListOf<RepoData?>()
                        for (item: RepoDataDao in it) {
                            Log.i("xx", " -- ${item.description}")
                            val repoData = RepoData(
                                item.name,
                                item.language,
                                item.description,
                                item.htmlUrl
                            )
                            listRepo.add(repoData)
                        }
                        callback.onDataLoaded(listRepo)
                    } else {
                        callback.onNotAvailable()
                    }
                }
            }, {
                callback.onError(it.message)
            })
    }

}