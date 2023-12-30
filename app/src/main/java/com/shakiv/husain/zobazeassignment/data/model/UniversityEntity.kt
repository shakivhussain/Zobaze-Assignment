package com.shakiv.husain.zobazeassignment.data.model

import com.google.gson.annotations.SerializedName

data class UniversityEntity(
    @SerializedName("name") val name: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("alpha_two_code") val alpha_two_code: String? = null,
    @SerializedName("domains") val domains: List<String>? = emptyList(),
    @SerializedName("web_pages") val web_pages: List<String>? = emptyList(),
)