package com.manish.koo.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.manish.koo.databinding.LayoutPostItemBinding

class PostAdapter : PagingDataAdapter<PostData, PostAdapter.PostViewHolder>(
    object : DiffUtil.ItemCallback<PostData>() {
        override fun areItemsTheSame(oldItem: PostData, newItem: PostData) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PostData, newItem: PostData) =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutPostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let { post ->
            holder.bind(post)
        }
    }

    inner class PostViewHolder constructor(private val binding: LayoutPostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(postData: PostData) {
            with(binding) {
                this.data = postData
                root.setOnClickListener {
                }
            }
        }
    }
}