package com.shakiv.husain.zobazeassignment.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.shakiv.husain.zobazeassignment.R
import com.shakiv.husain.zobazeassignment.databinding.FragmentUniversityDetailsBinding
import com.shakiv.husain.zobazeassignment.domain.University
import com.shakiv.husain.zobazeassignment.utils.getStringById
import com.shakiv.husain.zobazeassignment.utils.getUrl
import com.shakiv.husain.zobazeassignment.utils.openUrl

class UniversityDetailsScreen : Fragment() {

    private lateinit var binding: FragmentUniversityDetailsBinding
    private var university: University? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        university = arguments?.get(UNIVERISTY) as? University

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUniversityDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUi()
        bindListener()
    }


    private fun bindUi() {

        binding.apply {
            tvHeading.text = university?.name ?: getStringById(R.string.none)
            tvSubheading.text = university?.country.orEmpty()
            toolbar.tvTitle.text = getStringById(R.string.university_details)
            toolbar.tvTitle.setTextColor(
                ContextCompat.getColor(context ?: requireContext(), R.color.white)
            )
        }
    }

    private fun bindListener() {

        binding.apply {
            toolbar.buttonBack.isVisible = true
            toolbar.buttonBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnView.setOnClickListener {
                university?.let {
                    val url = getUrl(it)
                    if (url.isNotEmpty()) {
                        openUrl(url)
                    }
                }

            }
        }
    }

    companion object {

        const val UNIVERISTY = "key_university"

        fun open(navController: NavController, university: University) {

            val bundle = bundleOf(
                UNIVERISTY to university
            )

            navController.navigate(R.id.action_global_university_details, bundle)
        }
    }
}