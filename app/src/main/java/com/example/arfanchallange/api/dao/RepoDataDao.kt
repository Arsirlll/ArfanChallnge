package com.example.arfanchallange.api.dao


import com.google.gson.annotations.SerializedName

data class RepoDataDao(
    @SerializedName("name")
    val name: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("language")
    val language: String?
)