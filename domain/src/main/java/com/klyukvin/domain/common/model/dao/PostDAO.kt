package com.klyukvin.domain.common.model.dao

import com.klyukvin.domain.common.model.DomainPost

interface PostDAO {

    suspend fun loadPostsOnPage(
        currentPage: Int,
        pageSize: Int,
        offset: Int
    ): List<DomainPost>

    suspend fun loadPostById(postId: String): DomainPost

    suspend fun savePost(finalPostParameters: Map<String, Any>)

    fun generatePostId(): String

    suspend fun getPageCount(pageSize: Int): Int
}