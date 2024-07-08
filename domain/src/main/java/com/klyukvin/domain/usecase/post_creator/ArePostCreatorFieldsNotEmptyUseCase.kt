package com.klyukvin.domain.usecase.post_creator

import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorCategory
import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorFields

class ArePostCreatorFieldsNotEmptyUseCase {

    operator fun invoke(fields: DomainPostCreatorFields): Boolean {
        return with(fields) {
            imageStrings.isNotEmpty() &&
            title.trim().isNotEmpty() &&
            description.trim().isNotEmpty() &&
            category != DomainPostCreatorCategory.NOT_SELECTED &&
            phoneNumber.trim().isNotEmpty() &&
            address.trim().isNotEmpty()
        }
    }
}