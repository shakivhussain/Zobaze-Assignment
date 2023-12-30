package com.shakiv.husain.zobazeassignment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shakiv.husain.zobazeassignment.databinding.UniversityItemBinding
import com.shakiv.husain.zobazeassignment.domain.University

class UniversityAdapter(
    private val onItemClick: (University) -> Unit,
    private val onViewUniversity: (University) -> Unit
) : PagingDataAdapter<University, UniversityAdapter.UniversityViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val binding =
            UniversityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UniversityViewHolder(binding, onItemClick, onViewUniversity)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class UniversityViewHolder(
        private val universityItemBinding: UniversityItemBinding,
        private val onItemClick: (University) -> Unit,
        private val onViewUniversity: (University) -> Unit
    ) : RecyclerView.ViewHolder(universityItemBinding.root) {

        private var _university: University? = null

        init {
            with(universityItemBinding) {
                btnView.setOnClickListener {
                    _university?.let { item ->
                        onViewUniversity.invoke(item)
                    }
                }
                root.setOnClickListener {
                    _university?.let { item ->
                        onItemClick.invoke(item)
                    }

                }

            }
        }

        fun bind(position: Int) {
            val university = getItem(position)
            _university = university
            universityItemBinding.apply {
                university?.let {
                    tvTitle.text = it.name ?: "Unknown!"
                    tvDesc.text = it.country ?: "None!"
                }
            }
        }
    }


    companion object COMPARATOR : DiffUtil.ItemCallback<University>() {

        override fun areContentsTheSame(oldItem: University, newItem: University): Boolean {
            return false
        }

        override fun areItemsTheSame(oldItem: University, newItem: University): Boolean {

            return false
        }
    }

}