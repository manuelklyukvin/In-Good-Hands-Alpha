package com.klyukvin.domain.usecase.database.user

import com.klyukvin.domain.usecase.database.user.model.DomainUserErrorType

interface GetUserErrorUseCase {

    operator fun invoke(errorType: DomainUserErrorType?): String?
}