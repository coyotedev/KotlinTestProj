package com.example.coyotedev.testproj.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.coyotedev.testproj.data.UserPostComment
import com.example.coyotedev.testproj.view.CommentView

class CommentsAdapter(private val data: List<UserPostComment>):
    RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    class CommentViewHolder(val commentView: CommentView): RecyclerView.ViewHolder(commentView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val commentView = CommentView(parent.context)
        return CommentViewHolder(commentView)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.commentView.setName(data[position].name)
        holder.commentView.setEmail(data[position].email)
        holder.commentView.setBody(data[position].body)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}