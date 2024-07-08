package com.klyukvin.domain.usecase.database.image

import com.klyukvin.domain.repository.ImageRepository
import com.klyukvin.domain.usecase.database.image.model.DomainCompressedImageBytes

class SaveImageUseCase(
    private val imageRepository: ImageRepository,
) {

    suspend operator fun invoke(compressedImageBytes: DomainCompressedImageBytes) {
        imageRepository.saveImage(compressedImageBytes)
    }
}