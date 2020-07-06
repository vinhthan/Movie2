package com.thanmanhvinh.movieandnews.data.api

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("api_key")
    var apiKey: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("request_token")
    var requestToken: String
)

data class Login (
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("expires_at")
    var expiresAt: String,
    @SerializedName("request_token")
    var requestToken: String

)