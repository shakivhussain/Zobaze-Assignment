package com.shakiv.husain.zobazeassignment.presentation.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.shakiv.husain.zobazeassignment.domain.University
import com.shakiv.husain.zobazeassignment.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    suspend fun getUniversities(query: String): Flow<PagingData<University>> {
        return mainRepository.getUniversities(query)
    }

}