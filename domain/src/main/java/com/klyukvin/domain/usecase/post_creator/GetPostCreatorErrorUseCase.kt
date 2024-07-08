package com.klyukvin.domain.usecase.post_creator

import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorErrorType

interface GetPostCreatorErrorUseCase {

    operator fun invoke(errorType: DomainPostCreatorErrorType?): String?
}