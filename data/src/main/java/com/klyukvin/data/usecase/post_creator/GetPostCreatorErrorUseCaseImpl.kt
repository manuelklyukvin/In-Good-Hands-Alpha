package com.klyukvin.data.usecase.post_creator

import android.content.Context
import com.klyukvin.data.R
import com.klyukvin.domain.usecase.post_creator.GetPostCreatorErrorUseCase
import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorErrorType

class GetPostCreatorErrorUseCaseImpl(
    private val context: Context
) : GetPostCreatorErrorUseCase {

    override operator fun invoke(errorType: DomainPostCreatorErrorType?): String? {
        with(context) {
             return when (errorType) {
                DomainPostCreatorErrorType.IMAGES_IS_NOT_UNIQUE -> getString(R.string.post_creator_images_is_not_unique)
                 DomainPostCreatorErrorType.TITLE_IS_TOO_LONG -> getString(R.string.post_creator_title_is_too_long)
                 DomainPostCreatorErrorType.DESCRIPTION_IS_TOO_LONG -> getString(R.string.post_creator_description_is_too_long)
                 DomainPostCreatorErrorType.PHONE_NUMBER_IS_TOO_LONG -> getString(R.string.post_creator_phone_number_is_too_long)
                 DomainPostCreatorErrorType.ADDRESS_IS_TOO_LONG -> getString(R.string.post_creator_address_is_too_long)
                 null -> null
             }
        }
    }
}