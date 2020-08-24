package com.example.coyotedev.testproj.mvvm.viewmodel

import android.content.Context
import android.databinding.ObservableField
import com.example.coyotedev.testproj.data.UserPost
import com.example.coyotedev.testproj.data.UserPostComment
import com.example.coyotedev.testproj.mvvm.Model

class CommentsFragmentVM(private val ctx: Context, private val post: UserPost): Model.CallbackOnDataReady<List<UserPostComment>> {

    private val m_model = Model(ctx)
    val m_comments = ObservableField<List<UserPostComment>>()
    val m_isLoading = ObservableField<Boolean>()

    fun update() {
        m_isLoading.set(true)
        m_model.obtainUserPostComments(post, this)
    }

    override fun Success(data: List<UserPostComment>) {
        m_comments.set(data)
        m_isLoading.set(false)
    }

    override fun Error(e: Throwable) {
        e.printStackTrace()
        m_isLoading.set(false)
    }
}