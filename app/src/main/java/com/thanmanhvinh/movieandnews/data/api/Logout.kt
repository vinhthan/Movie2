package com.thanmanhvinh.movieandnews.data.api


import com.google.gson.annotations.SerializedName

data class LogoutRequest(
    @SerializedName("api_key")
    var apiKey: String,
    @SerializedName("session_id")
    var sessionId: String
)

data class Logout(
    @SerializedName("status_code")
    var statusCode: Int,
    @SerializedName("status_message")
    var statusMessage: String
)