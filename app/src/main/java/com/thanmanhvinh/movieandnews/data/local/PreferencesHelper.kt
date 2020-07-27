package com.thanmanhvinh.movieandnews.data.local

interface PreferencesHelper {
    val accessToken: String
    val username: String
    val password: String

    fun savePassword(password: String)
    fun setAccessToken(token: String)

    fun checkLogin(): Boolean
    fun setAccount(username: String?, password: String?, token: String?)


    fun saveAccount(username: String, password: String, token: String)

/*
    val isFirst: String
    fun setIsFirst(yes: String?)
*/

}