package com.example.coyotedev.testproj.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.coyotedev.testproj.data.UserPost
import com.example.coyotedev.testproj.view.PostView

class PostsAdapter(private val data: List<UserPost>):
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    interface DelegateOnUserPostClick {
        fun onClick(post: UserPost)
    }

    private var m_recyclerView: RecyclerView? = null
    private var m_delegateShowCommentsFragment: DelegateOnUserPostClick? = null
    private val m_onClickListener = object: View.OnClickListener{
        override fun onClick(v: View?) {
            val pos = m_recyclerView?.getChildLayoutPosition(v!!)
            m_delegateShowCommentsFragment?.onClick(data[pos!!])
        }
    }

    class PostViewHolder(val postView: PostView) : RecyclerView.ViewHolder(postView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView = PostView(parent.context)
        postView.setOnClickListener(m_onClickListener)
        return PostViewHolder(postView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.postView.setTitleString(data[position].title)
        holder.postView.setBodyString(data[position].body)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        m_recyclerView = recyclerView
    }

    fun setDelegateOnUserPostClick(delegate: DelegateOnUserPostClick) {
        m_delegateShowCommentsFragment = delegate
    }
}