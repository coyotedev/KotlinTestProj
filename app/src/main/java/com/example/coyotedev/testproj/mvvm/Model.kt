package com.example.coyotedev.testproj.mvvm

import android.content.Context
import com.example.coyotedev.testproj.api.JsonplaceholderApi
import com.example.coyotedev.testproj.data.UserData
import com.example.coyotedev.testproj.data.UserPost
import com.example.coyotedev.testproj.data.UserPostComment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class Model(private val ctx: Context) {

    private var m_api: JsonplaceholderApi

    init {
        m_api = JsonplaceholderApi.newInstance(ctx)
    }

    fun obtainUserPosts(callback: CallbackOnDataReady<List<UserPost>>) {
        m_api.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    result ->
                    callback.Success(result)
                }, {
                    error ->
                    callback.Error(error)
                }
            )
    }

    fun obtainUserPostComments(post: UserPost, callback: CallbackOnDataReady<List<UserPostComment>>) {
        m_api.getComments(post.id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    result ->
                    callback.Success(result)
                }, {
                    error ->
                    callback.Error(error)
                }
            )
    }

    interface CallbackOnDataReady<T: List<UserData>> {
        fun Success(data: T)
        fun Error(e: Throwable)
    }
}