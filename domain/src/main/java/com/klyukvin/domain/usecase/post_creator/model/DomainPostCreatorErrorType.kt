package com.klyukvin.domain.usecase.post_creator.model

enum class DomainPostCreatorErrorType {
    IMAGES_IS_NOT_UNIQUE,
    TITLE_IS_TOO_LONG,
    DESCRIPTION_IS_TOO_LONG,
    PHONE_NUMBER_IS_TOO_LONG,
    ADDRESS_IS_TOO_LONG
}