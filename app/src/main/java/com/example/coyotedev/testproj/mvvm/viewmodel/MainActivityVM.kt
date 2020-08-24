package com.example.coyotedev.testproj.mvvm.viewmodel

import android.content.Context
import android.databinding.ObservableField
import com.example.coyotedev.testproj.data.UserPost
import com.example.coyotedev.testproj.mvvm.Model

class MainActivityVM(private val ctx: Context): Model.CallbackOnDataReady<List<UserPost>> {

    private val m_model = Model(ctx)
    val m_posts = ObservableField<List<UserPost>>()
    val m_isLoading = ObservableField<Boolean>()

    fun update() {
        m_isLoading.set(true)
        m_model.obtainUserPosts(this)
    }

    override fun Success(data: List<UserPost>) {
        m_posts.set(data)
        m_isLoading.set(false)
    }

    override fun Error(e: Throwable) {
        e.printStackTrace()
        m_isLoading.set(false)
    }
}