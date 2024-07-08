package com.klyukvin.domain.usecase.database.post

import com.klyukvin.domain.repository.PostRepository

class GetPageCountUseCase(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(): Int {
        return postRepository.getPageCount()
    }
}