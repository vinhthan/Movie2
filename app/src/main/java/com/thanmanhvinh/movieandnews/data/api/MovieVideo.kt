package com.thanmanhvinh.movieandnews.data.api


import com.google.gson.annotations.SerializedName

data class MovieVideoRequest (
    @SerializedName("api_key")
    var apiKey: String
)

data class MovieVideo(
    var id: Int,
    var results: List<Result>
) {
    data class Result(
        var id: String,
        @SerializedName("iso_3166_1")
        var iso31661: String,
        @SerializedName("iso_639_1")
        var iso6391: String,
        var key: String,
        var name: String,
        var site: String,
        var size: Int,
        var type: String
    )
}