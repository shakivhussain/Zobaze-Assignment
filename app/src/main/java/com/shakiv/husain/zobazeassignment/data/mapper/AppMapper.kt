package com.shakiv.husain.zobazeassignment.data.mapper

import com.shakiv.husain.zobazeassignment.data.model.UniversityEntity
import com.shakiv.husain.zobazeassignment.domain.University

fun UniversityEntity.toUniversity() : University{
    return University(
        name = name,
        country = country,
        alphaTwoCode = alpha_two_code,
        domains = domains,
        webPages = web_pages
    )
}