package com.klyukvin.domain.usecase

interface CompressImageUseCase {

    suspend operator fun invoke(imageString: String): ByteArray
}