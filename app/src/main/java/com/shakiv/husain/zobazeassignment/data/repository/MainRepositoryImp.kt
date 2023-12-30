package com.shakiv.husain.zobazeassignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shakiv.husain.zobazeassignment.data.service.UniversityService
import com.shakiv.husain.zobazeassignment.domain.University
import com.shakiv.husain.zobazeassignment.domain.repository.MainRepository
import com.shakiv.husain.zobazeassignment.presentation.pagingSource.UniversityPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    private val universityService: UniversityService
) : MainRepository {

    override suspend fun getUniversities(query: String): Flow<PagingData<University>> {
        val config = PagingConfig(20, 4, true, 20)
        return Pager(config) {
            UniversityPagingSource(query,universityService)
        }.flow
    }

}

