package com.thanmanhvinh.movieandnews.data.api


import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("api_key")
    var apiKey: String
)

data class Token(
    @SerializedName("expires_at")
    var expiresAt: String,
    @SerializedName("request_token")
    var requestToken: String,
    @SerializedName("success")
    var success: Boolean
)