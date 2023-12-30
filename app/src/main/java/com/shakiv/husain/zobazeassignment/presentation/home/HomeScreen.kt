package com.shakiv.husain.zobazeassignment.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.shakiv.husain.zobazeassignment.R
import com.shakiv.husain.zobazeassignment.databinding.FragmentHomeBinding
import com.shakiv.husain.zobazeassignment.domain.University
import com.shakiv.husain.zobazeassignment.presentation.adapter.UniversityAdapter
import com.shakiv.husain.zobazeassignment.presentation.details.UniversityDetailsScreen
import com.shakiv.husain.zobazeassignment.utils.getStringById
import com.shakiv.husain.zobazeassignment.utils.getUrl
import com.shakiv.husain.zobazeassignment.utils.openUrl
import com.shakiv.husain.zobazeassignment.utils.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var universityAdapter: UniversityAdapter

    private val onItemClick: (University) -> Unit = {
        UniversityDetailsScreen.open(findNavController(), it)
    }

    private val onViewItemClick: (University) -> Unit = {

        val url = getUrl(it)
        if (url.isNotEmpty()) {
            openUrl(url)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        universityAdapter = UniversityAdapter(onItemClick, onViewItemClick)

        searchQuery("India")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bindUI()
        bindObserver()

    }

    private fun bindObserver() {


        binding.searchContainer.etSearch.textChanges().debounce(300)
            .onEach {
                val query = it.toString()
                searchQuery(query)
            }
            .launchIn(lifecycleScope)

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            universityAdapter.loadStateFlow.collectLatest {

                val isLoading = (it.refresh is LoadState.Loading) && universityAdapter.itemCount < 1
                binding.loadingProgress.isVisible = isLoading

                when (it.refresh) {

                    is LoadState.Loading -> {
                    }

                    is LoadState.NotLoading -> {
                        binding.tvNothingToShow.isVisible = (universityAdapter.itemCount < 1)
                    }

                    is LoadState.Error -> {
                        binding.tvError.isVisible = true
                        binding.tvError.text = getStringById(R.string.something_went_wrong)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun searchQuery(query: String) {
        lifecycleScope.launch {
            homeViewModel.getUniversities(query).distinctUntilChanged().collectLatest {
                universityAdapter.submitData(it)
            }
        }
    }

    private fun bindUI() {
        binding.apply {
            universityRecycler.adapter = universityAdapter
            universityRecycler.setHasFixedSize(true)
        }
    }
}