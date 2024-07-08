package com.klyukvin.domain.usecase.database.image.model

data class DomainCompressedImageBytes(
    val name: String,
    val imageBytes: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }

        other as DomainCompressedImageBytes

        if (name != other.name) {
            return false
        }
        if (!imageBytes.contentEquals(other.imageBytes)) {
            return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result += imageBytes.contentHashCode()
        return result
    }
}