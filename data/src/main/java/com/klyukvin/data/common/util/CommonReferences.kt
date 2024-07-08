package com.klyukvin.data.common.util

sealed class CommonReferences(val reference: String) {

    data object PostDatabase : CommonReferences(POST_DATABASE_REFERENCE)
    data object UserDatabase : CommonReferences(USER_DATABASE_REFERENCE)
    data object ImageStorage : CommonReferences(IMAGE_STORAGE_REFERENCE)

    private companion object {

        const val POST_DATABASE_REFERENCE = "post"
        const val USER_DATABASE_REFERENCE = "user"
        const val IMAGE_STORAGE_REFERENCE = "image"
    }
}