package com.klyukvin.data.usecase.database.user

import android.content.Context
import com.klyukvin.data.R
import com.klyukvin.domain.usecase.database.user.GetUserErrorUseCase
import com.klyukvin.domain.usecase.database.user.model.DomainUserErrorType

class GetUserErrorUseCaseImpl(
    private val context: Context
) : GetUserErrorUseCase {

    override operator fun invoke(errorType: DomainUserErrorType?): String? {
        with(context) {
            return when (errorType) {
                DomainUserErrorType.COULD_NOT_LOGIN -> getString(R.string.user_could_not_login)
                null -> null
            }
        }
    }
}