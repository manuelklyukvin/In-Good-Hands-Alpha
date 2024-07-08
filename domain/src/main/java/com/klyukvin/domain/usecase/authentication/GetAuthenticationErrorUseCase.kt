package com.klyukvin.domain.usecase.authentication

import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationErrorType

interface GetAuthenticationErrorUseCase {

    operator fun invoke(errorType: DomainAuthenticationErrorType?): String?
}