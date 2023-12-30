package com.shakiv.husain.zobazeassignment.domain.repository

import androidx.paging.PagingData
import com.shakiv.husain.zobazeassignment.domain.University
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getUniversities(query: String): Flow<PagingData<University>>

}