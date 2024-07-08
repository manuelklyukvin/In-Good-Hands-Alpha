package com.klyukvin.domain.common.model.dao

import com.klyukvin.domain.usecase.database.image.model.DomainCompressedImageBytes

interface ImageDAO {

    suspend fun saveImage(compressedImageBytes: DomainCompressedImageBytes)

    suspend fun getImageLinkByName(imageName: String): String
}