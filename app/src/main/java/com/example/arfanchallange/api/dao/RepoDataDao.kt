package com.example.arfanchallange.api.dao

import com.google.gson.annotations.SerializedName


data class RepoDataDao(
    val name: String?,
    val htmlUrl: String?,
    val description: String?,
    val language: String?
)