package com.example.coyotedev.testproj.api

import android.content.Context
import com.example.coyotedev.testproj.R
import com.example.coyotedev.testproj.data.UserPost
import com.example.coyotedev.testproj.data.UserPostComment
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonplaceholderApi {
    @GET("posts")
    fun getPosts() : Observable<List<UserPost>>

    @GET("comments")
    fun getComments(@Query("postId")postId: Int) : Observable<List<UserPostComment>>

    companion object {
        fun newInstance(ctx: Context): JsonplaceholderApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ctx.resources.getString(R.string.apiDomain))
                .build()

            return retrofit.create(JsonplaceholderApi::class.java)
        }
    }
}