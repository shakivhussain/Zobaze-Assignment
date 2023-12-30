package com.shakiv.husain.zobazeassignment.presentation.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shakiv.husain.zobazeassignment.data.mapper.toUniversity
import com.shakiv.husain.zobazeassignment.data.network.ApiResponse
import com.shakiv.husain.zobazeassignment.data.network.NetworkRequest
import com.shakiv.husain.zobazeassignment.data.service.UniversityService
import com.shakiv.husain.zobazeassignment.domain.University

class UniversityPagingSource(
    private val query: String,
    private val universityService: UniversityService
) : PagingSource<Int, University>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, University> {
        val key = params.key ?: 1
        val errorMessage = "Something went wrong!"

        return runCatching {

            val universities = NetworkRequest.process {
                universityService.getUniversities(query)
            }.run {

                when (this) {
                    is ApiResponse.Success -> {
                        this.results ?: throw Exception(errorMessage)
                    }

                    is ApiResponse.Failure -> {
                        throw Exception(errorMessage)
                    }
                }
            }

            val universityList = universities.map { it.toUniversity() }

            LoadResult.Page(
                universityList, null, nextKey = if (universities.isEmpty()) null else key + 1
            )

        }.getOrElse {
            it.printStackTrace()
            LoadResult.Error(it)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, University>): Int? {
        return 1;
    }
}