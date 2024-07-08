package com.klyukvin.domain.repository

import com.klyukvin.domain.usecase.database.image.model.DomainCompressedImageBytes

interface ImageRepository {

    suspend fun saveImage(compressedImageBytes: DomainCompressedImageBytes)

    suspend fun getImageLinkByName(imageName: String): String
}