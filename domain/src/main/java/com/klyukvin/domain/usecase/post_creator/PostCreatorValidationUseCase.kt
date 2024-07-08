package com.klyukvin.domain.usecase.post_creator

import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorErrorType
import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorErrors
import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorFieldType
import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorFields

class PostCreatorValidationUseCase(
    private val getPostCreatorErrorUseCase: GetPostCreatorErrorUseCase
) {

    operator fun invoke(fields: DomainPostCreatorFields): DomainPostCreatorErrors {
        with(fields) {
            val imagesValidationResult = validateImages(imageStrings)
            val titleValidationResult = validateTitle(title)
            val descriptionValidationResult = validateDescription(description)
            val phoneNumberValidationResult = validatePhoneNumber(phoneNumber)
            val addressValidationResult = validateAddress(address)

            val imagesError = getPostCreatorErrorUseCase(imagesValidationResult)
            val titleError = getPostCreatorErrorUseCase(titleValidationResult)
            val descriptionError = getPostCreatorErrorUseCase(descriptionValidationResult)
            val phoneNumberError = getPostCreatorErrorUseCase(phoneNumberValidationResult)
            val addressError = getPostCreatorErrorUseCase(addressValidationResult)

            return DomainPostCreatorErrors(
                imagesError = imagesError,
                titleError = titleError,
                descriptionError = descriptionError,
                categoryError = null,
                phoneNumberError = phoneNumberError,
                addressError = addressError
            )
        }
    }

    private fun validateImages(imageStrings: List<String>): DomainPostCreatorErrorType? {
        val imageSet = imageStrings.toSet()
        if (imageSet.size != imageStrings.size) {
            return DomainPostCreatorErrorType.IMAGES_IS_NOT_UNIQUE
        }
        return null
    }

    private fun validateTitle(title: String): DomainPostCreatorErrorType? {
        return validateFieldLength(title, DomainPostCreatorFieldType.TITLE)
    }

    private fun validateDescription(description: String): DomainPostCreatorErrorType? {
        return validateFieldLength(description, DomainPostCreatorFieldType.DESCRIPTION)
    }

    private fun validatePhoneNumber(phoneNumber: String): DomainPostCreatorErrorType? {
        return validateFieldLength(phoneNumber, DomainPostCreatorFieldType.PHONE_NUMBER)
    }

    private fun validateAddress(address: String): DomainPostCreatorErrorType? {
        return validateFieldLength(address, DomainPostCreatorFieldType.ADDRESS)
    }

    private fun validateFieldLength(field: String, fieldType: DomainPostCreatorFieldType): DomainPostCreatorErrorType? {
        val maxLength = getMaxLength(fieldType)
        val fieldLengthError = getFieldLengthError(fieldType)

        if (maxLength != null && fieldLengthError != null && field.length > maxLength) {
            return fieldLengthError
        }
        return null
    }

    private fun getMaxLength(fieldType: DomainPostCreatorFieldType): Int? {
        return when (fieldType) {
            DomainPostCreatorFieldType.TITLE -> MAX_TITLE_LENGTH
            DomainPostCreatorFieldType.DESCRIPTION -> MAX_DESCRIPTION_LENGTH
            DomainPostCreatorFieldType.PHONE_NUMBER -> MAX_PHONE_NUMBER_LENGTH
            DomainPostCreatorFieldType.ADDRESS -> MAX_ADDRESS_LENGTH
            else -> null
        }
    }

    private fun getFieldLengthError(fieldType: DomainPostCreatorFieldType): DomainPostCreatorErrorType? {
        return when (fieldType) {
            DomainPostCreatorFieldType.TITLE -> DomainPostCreatorErrorType.TITLE_IS_TOO_LONG
            DomainPostCreatorFieldType.DESCRIPTION -> DomainPostCreatorErrorType.DESCRIPTION_IS_TOO_LONG
            DomainPostCreatorFieldType.PHONE_NUMBER -> DomainPostCreatorErrorType.PHONE_NUMBER_IS_TOO_LONG
            DomainPostCreatorFieldType.ADDRESS -> DomainPostCreatorErrorType.ADDRESS_IS_TOO_LONG
            else -> null
        }
    }

    private companion object {

        const val MAX_TITLE_LENGTH = 100
        const val MAX_DESCRIPTION_LENGTH = 500
        const val MAX_PHONE_NUMBER_LENGTH = 20
        const val MAX_ADDRESS_LENGTH = 50
    }
}