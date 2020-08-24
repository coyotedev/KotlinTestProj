package com.example.coyotedev.testproj.data

data class UserPostComment (
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
) : UserData
