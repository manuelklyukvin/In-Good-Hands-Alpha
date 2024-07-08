package com.klyukvin.data.repository

import com.klyukvin.domain.common.model.DomainPost
import com.klyukvin.domain.common.model.dao.PostDAO
import com.klyukvin.domain.repository.PostRepository

class PostRepositoryImpl(
    private val postDao: PostDAO
) : PostRepository {

    override suspend fun loadPostsOnPage(currentPage: Int, offset: Int): List<DomainPost> {
        return postDao.loadPostsOnPage(
            currentPage = currentPage,
            pageSize = pageSize,
            offset = offset
        )
    }

    override suspend fun loadPostById(postId: String): DomainPost {
        return postDao.loadPostById(postId)
    }

    override suspend fun savePost(finalPostParameters: Map<String, Any>) {
        postDao.savePost(finalPostParameters)
    }

    override fun generatePostId(): String {
        return postDao.generatePostId()
    }

    override suspend fun getPageCount(): Int {
        return postDao.getPageCount(pageSize)
    }
}