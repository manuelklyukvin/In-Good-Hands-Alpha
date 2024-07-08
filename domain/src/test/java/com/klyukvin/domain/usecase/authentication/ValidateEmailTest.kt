package com.klyukvin.domain.usecase.authentication

import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationErrorType
import org.junit.Assert
import org.junit.Test

class ValidateEmailTest {

    private val useCase = ValidateEmailUseCase(CommonValidateFieldUseCase())

    private var email: String? = null

    private var actual: Result<Unit, DomainAuthenticationErrorType>? = null
    private var expected: Result<Unit, DomainAuthenticationErrorType>? = null

    @Test
    fun testValidEmail() {
        email = "test_email@example.com"

        actual = useCase(email!!)
        expected = Result.Success(Unit)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidEmail() {
        email = "test#-example.com"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testShortEmail() {
        email = "a"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_SHORT)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testLongEmail() {
        email = "abcdefghijklmnopqrstuvwxyz@gmail.com"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_LONG)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmailWithSpaces() {
        email = "test @ example.com"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmailWithoutAt() {
        email = "testexample.com"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.EMAIL_IS_INVALID)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmailWithoutDot() {
        email = "test@examplecom"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.EMAIL_IS_INVALID)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmailWithoutName() {
        email = "@example.com"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.EMAIL_IS_INVALID)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmailWithoutMailServer() {
        email = "test@.com"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.EMAIL_IS_INVALID)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmailWithoutDomain() {
        email = "test@example."

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.EMAIL_IS_INVALID)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testTooMuchAts() {
        email = "test_email@@example.com"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.EMAIL_IS_INVALID)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testTooMuchDots() {
        email = "test_email@example..com"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.EMAIL_IS_INVALID)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testTooMuchSpecialSymbols() {
        email = "test_email@@example..com"

        actual = useCase(email!!)
        expected = Result.Error(DomainAuthenticationErrorType.EMAIL_IS_INVALID)
        Assert.assertEquals(actual, expected)
    }
}