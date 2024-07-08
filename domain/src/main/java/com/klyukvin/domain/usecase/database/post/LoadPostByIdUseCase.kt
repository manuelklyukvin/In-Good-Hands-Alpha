package com.klyukvin.domain.usecase.database.post

import com.klyukvin.domain.common.model.DomainPost
import com.klyukvin.domain.repository.PostRepository

class LoadPostByIdUseCase(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(postId: String): DomainPost {
        return postRepository.loadPostById(postId)
    }
}