package com.educationalapp.features.video.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.educationalapp.databinding.ItemCommentBinding
import com.educationalapp.features.profile.logic.User
import com.educationalapp.features.video.logic.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    var data: List<Comment> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    var users: List<User> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentBinding.inflate(inflater, parent, false)

        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = data[position]

        with(holder.binding) {
            usernameTextView.text = users.firstOrNull { it.id == comment.userId }?.username
            commentTextView.text = comment.text
        }
    }
}