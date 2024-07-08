package com.klyukvin.domain.usecase.database.post

import com.klyukvin.domain.repository.PostRepository

class GeneratePostIdUseCase(
    private val postRepository: PostRepository
) {

    operator fun invoke(): String {
        return postRepository.generatePostId()
    }
}