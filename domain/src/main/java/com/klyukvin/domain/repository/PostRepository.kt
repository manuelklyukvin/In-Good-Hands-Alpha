package com.klyukvin.domain.repository

import com.klyukvin.domain.common.model.DomainPost

interface PostRepository {

    suspend fun loadPostsOnPage(currentPage: Int, offset: Int): List<DomainPost>

    suspend fun loadPostById(postId: String): DomainPost

    suspend fun savePost(finalPostParameters: Map<String, Any>)

    fun generatePostId(): String

    suspend fun getPageCount(): Int

    val pageSize: Int
        get() = 5
}