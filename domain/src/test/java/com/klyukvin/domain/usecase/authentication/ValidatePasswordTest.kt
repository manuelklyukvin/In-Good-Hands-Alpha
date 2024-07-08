package com.klyukvin.domain.usecase.authentication

import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationErrorType
import org.junit.Assert
import org.junit.Test

class ValidatePasswordTest {

    private val useCase = ValidatePasswordUseCase(CommonValidateFieldUseCase())

    private var password: String? = null

    private var actual: Result<Unit, DomainAuthenticationErrorType>? = null
    private var expected: Result<Unit, DomainAuthenticationErrorType>? = null

    @Test
    fun testValidPassword() {
        password = "12345678A_b-"

        actual = useCase(password!!)
        expected = Result.Success(Unit)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidPassword() {
        password = "Миша!@2012"

        actual = useCase(password!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testShortPassword() {
        password = "1234"

        actual = useCase(password!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_SHORT)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testLongPassword() {
        password = "1111111111111111111111111111111111111111"

        actual = useCase(password!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_LONG)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testPasswordWithSpaces() {
        password = "1234 pass"

        actual = useCase(password!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }
}