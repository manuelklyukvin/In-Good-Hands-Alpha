package com.klyukvin.data.common.util

sealed class UserReferences(val reference: String) {

    data object Id : UserReferences(ID_REFERENCE)
    data object Username : UserReferences(USERNAME_REFERENCE)
    data object Firstname : UserReferences(FIRSTNAME_REFERENCE)
    data object Lastname : UserReferences(LASTNAME_REFERENCE)
    data object Email : UserReferences(EMAIL_REFERENCE)
    data object Password : UserReferences(PASSWORD_REFERENCE)
    data object IsAdmin : UserReferences(IS_ADMIN_REFERENCE)

    private companion object {

        const val ID_REFERENCE = "id"
        const val USERNAME_REFERENCE = "username"
        const val FIRSTNAME_REFERENCE = "firstname"
        const val LASTNAME_REFERENCE = "lastname"
        const val EMAIL_REFERENCE = "email"
        const val PASSWORD_REFERENCE = "password"
        const val IS_ADMIN_REFERENCE = "is_admin"
    }
}