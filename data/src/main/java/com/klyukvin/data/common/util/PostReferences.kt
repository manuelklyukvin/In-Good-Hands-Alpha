package com.klyukvin.data.common.util

sealed class PostReferences(val reference: String) {

    data object Id : PostReferences(ID_REFERENCE)
    data object UserId : PostReferences(USER_ID_REFERENCE)
    data object Images : PostReferences(IMAGES_REFERENCE)
    data object Title : PostReferences(TITLE_REFERENCE)
    data object Description : PostReferences(DESCRIPTION_REFERENCE)
    data object Category : PostReferences(CATEGORY_REFERENCE)
    data object PhoneNumber : PostReferences(PHONE_NUMBER_REFERENCE)
    data object Address : PostReferences(ADDRESS_REFERENCE)
    data object Date : PostReferences(DATE_REFERENCE)

    private companion object {

        const val ID_REFERENCE = "id"
        const val USER_ID_REFERENCE = "user_id"
        const val IMAGES_REFERENCE = "images"
        const val TITLE_REFERENCE = "title"
        const val DESCRIPTION_REFERENCE = "description"
        const val CATEGORY_REFERENCE = "category"
        const val PHONE_NUMBER_REFERENCE = "phone_number"
        const val ADDRESS_REFERENCE = "address"
        const val DATE_REFERENCE = "date"
    }
}