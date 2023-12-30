package com.shakiv.husain.zobazeassignment.domain

import java.io.Serializable

data class University(
    val name: String? = null,
    val country: String? = null,
    val alphaTwoCode: String? = null,
    val domains: List<String>? = emptyList(),
    val webPages: List<String>? = emptyList(),
) : Serializable