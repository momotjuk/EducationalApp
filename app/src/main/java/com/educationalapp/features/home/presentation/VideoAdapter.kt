package com.educationalapp.features.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.educationalapp.R
import com.educationalapp.databinding.ItemVideoBinding
import com.educationalapp.features.home.logic.Like
import com.educationalapp.features.home.domain.model.Video

class VideoAdapter() : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    var onItemClick: ((Long) -> Unit)? = null

    var data: List<Video> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class VideoViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVideoBinding.inflate(inflater, parent, false)

        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = data[position]
        val context = holder.itemView.context

        holder.itemView.setOnClickListener {
                onItemClick?.invoke(video.id)
        }

        with(holder.binding) {
            nameTextView.text = video.name

            when (video.likeStatus) {
                Like.LIKED ->
                    likedImageView.setColorFilter(
                        ContextCompat.getColor(context, R.color.white),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                Like.DISLIKED ->
                    dislikedImageView.setColorFilter(
                        ContextCompat.getColor(context, R.color.white ),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                else -> {
                    dislikedImageView.setColorFilter(
                        ContextCompat.getColor(context, R.color.black ),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    likedImageView.setColorFilter(
                        ContextCompat.getColor(context, R.color.black),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                }
            }

            Glide.with(context).load(video.videoURL).circleCrop()
                .error(R.drawable.ic_video)
                .placeholder(R.drawable.ic_video).into(imageView)
        }
    }
}
