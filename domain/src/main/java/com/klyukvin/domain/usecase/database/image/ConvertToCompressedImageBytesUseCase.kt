package com.klyukvin.domain.usecase.database.image

import com.klyukvin.domain.usecase.CompressImageUseCase
import com.klyukvin.domain.usecase.database.image.model.DomainCompressedImageBytes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class ConvertToCompressedImageBytesUseCase(
    private val compressImageUseCase: CompressImageUseCase
) {

    suspend operator fun invoke(imageStrings: List<String>): Flow<DomainCompressedImageBytes> {
        return flow {
            imageStrings.forEach { imageString ->
                val imageName = generateImageName()
                val imageBytes = compressImageUseCase(imageString)
                emit(DomainCompressedImageBytes(imageName, imageBytes))
            }
        }
    }

    private fun generateImageName(): String {
        return UUID.randomUUID().toString()
    }
}