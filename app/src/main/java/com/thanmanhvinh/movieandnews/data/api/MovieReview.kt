package com.thanmanhvinh.movieandnews.data.api


import com.google.gson.annotations.SerializedName

data class MovieReviewRequest(
    @SerializedName("api_key")
    var apiKey: String
)

data class MovieReview(
    var id: Int,
    var page: Int,
    var results: List<Result>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
) {
    data class Result(
        var author: String,
        var content: String,
        var id: String,
        var url: String
    )
}