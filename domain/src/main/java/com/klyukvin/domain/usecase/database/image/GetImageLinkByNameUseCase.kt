package com.klyukvin.domain.usecase.database.image

import com.klyukvin.domain.repository.ImageRepository

class GetImageLinkByNameUseCase(
    private val imageRepository: ImageRepository
) {

    suspend operator fun invoke(imageName: String): String {
        return imageRepository.getImageLinkByName(imageName)
    }
}