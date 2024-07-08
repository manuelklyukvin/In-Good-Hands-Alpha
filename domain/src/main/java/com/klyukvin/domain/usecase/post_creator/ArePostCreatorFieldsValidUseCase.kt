package com.klyukvin.domain.usecase.post_creator

import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorErrors

class ArePostCreatorFieldsValidUseCase {
    
    operator fun invoke(errors: DomainPostCreatorErrors): Boolean {
        return with(errors) {
            imagesError == null &&
            titleError == null &&
            descriptionError == null &&
            categoryError == null &&
            phoneNumberError == null &&
            addressError == null
        }
    }
}