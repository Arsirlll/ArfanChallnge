package com.example.arfanchallange.api

import android.database.Observable
import com.example.arfanchallange.api.dao.MainDataDao
import com.example.arfanchallange.api.dao.RepoDataDao
import com.example.arfanchallange.util.Constant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{username}")
    fun getMainData(
        @Path("username") username: String
    ): Observable<MainDataDao>

    @GET("https://api.github.com/users/{username}/repos")
    fun getReposData(
        @Path("usernmae") username: String
    ): Observable<List<RepoDataDao>>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}