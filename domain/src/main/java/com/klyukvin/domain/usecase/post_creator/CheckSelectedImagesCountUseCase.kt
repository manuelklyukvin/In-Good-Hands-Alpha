package com.klyukvin.domain.usecase.post_creator

class CheckSelectedImagesCountUseCase {

    operator fun invoke(count: Int): Boolean {
        return count < MAX_IMAGES_COUNT
    }

    private companion object {

        const val MAX_IMAGES_COUNT = 5
    }
}