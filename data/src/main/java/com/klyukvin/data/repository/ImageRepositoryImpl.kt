package com.klyukvin.data.repository

import com.klyukvin.domain.common.model.dao.ImageDAO
import com.klyukvin.domain.repository.ImageRepository
import com.klyukvin.domain.usecase.database.image.model.DomainCompressedImageBytes

class ImageRepositoryImpl(
    private val imageDao: ImageDAO
) : ImageRepository {

    override suspend fun saveImage(compressedImageBytes: DomainCompressedImageBytes) {
        imageDao.saveImage(compressedImageBytes)
    }

    override suspend fun getImageLinkByName(imageName: String): String {
        return imageDao.getImageLinkByName(imageName)
    }
}