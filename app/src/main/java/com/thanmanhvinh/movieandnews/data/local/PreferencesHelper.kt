package com.thanmanhvinh.movieandnews.data.local

interface PreferencesHelper {
    val accessToken: String
    val username: String
    val password: String

    fun savePassword(password: String)
    fun setAccessToken(token: String)


}