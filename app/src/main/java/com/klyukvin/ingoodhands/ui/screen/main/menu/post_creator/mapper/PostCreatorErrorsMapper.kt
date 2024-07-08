package com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.mapper

import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorErrors
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model.UiPostCreatorErrors

class PostCreatorErrorsMapper {

    fun mapToUi(errors: DomainPostCreatorErrors): UiPostCreatorErrors {
        with(errors) {
            return UiPostCreatorErrors(
                imagesError = imagesError,
                titleError = titleError,
                descriptionError = descriptionError,
                categoryError = categoryError,
                phoneNumberError = phoneNumberError,
                addressError = addressError
            )
        }
    }

    fun mapToDomain(errors: UiPostCreatorErrors): DomainPostCreatorErrors {
        with(errors) {
            return DomainPostCreatorErrors(
                imagesError = imagesError,
                titleError = titleError,
                descriptionError = descriptionError,
                categoryError = categoryError,
                phoneNumberError = phoneNumberError,
                addressError = addressError
            )
        }
    }
}