package com.klyukvin.domain.usecase.database.post

import com.klyukvin.domain.common.model.DomainPost
import com.klyukvin.domain.repository.PostRepository

class LoadPostsOnPageUseCase(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(currentPage: Int): List<DomainPost> {
        val offset = getPageOffset(currentPage, postRepository.pageSize)
        return postRepository.loadPostsOnPage(currentPage, offset)
    }

    private fun getPageOffset(currentPage: Int, pageSize: Int): Int {
        return (currentPage - 1) * pageSize
    }
}