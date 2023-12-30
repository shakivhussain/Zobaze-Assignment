package com.shakiv.husain.zobazeassignment.data.service

import com.shakiv.husain.zobazeassignment.data.model.UniversityEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityService {

    @GET("search")
    suspend fun getUniversities(
        @Query("country") country : String
    ): Response<List<UniversityEntity>>

}