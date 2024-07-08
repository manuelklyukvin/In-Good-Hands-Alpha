package com.klyukvin.domain.usecase.authentication

import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationErrorType
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationFieldType
import org.junit.Assert
import org.junit.Test

class CommonValidateFieldTest {

    private val useCase = CommonValidateFieldUseCase()

    private var field: String? = null
    private var authenticationFieldType: DomainAuthenticationFieldType? = null

    private var actual:  Result<Unit, DomainAuthenticationErrorType>? = null
    private var expected: Result<Unit, DomainAuthenticationErrorType>? = null

    @Test
    fun testValidUsername() {
        field = "ivan_ivanov"
        authenticationFieldType = DomainAuthenticationFieldType.USERNAME

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Success(Unit)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidUsername() {
        field = "kostya@"
        authenticationFieldType = DomainAuthenticationFieldType.USERNAME

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testShortUsername() {
        field = "a"
        authenticationFieldType = DomainAuthenticationFieldType.USERNAME

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_SHORT)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testLongUsername() {
        field = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        authenticationFieldType = DomainAuthenticationFieldType.USERNAME

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_LONG)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testUsernameWithSpaces() {
        field = "ivan ivanov"
        authenticationFieldType = DomainAuthenticationFieldType.USERNAME

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testValidName() {
        field = "Иван"
        authenticationFieldType = DomainAuthenticationFieldType.NAME

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Success(Unit)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidName() {
        field = "Миша-1"
        authenticationFieldType = DomainAuthenticationFieldType.NAME

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testShortName() {
        field = "М"
        authenticationFieldType = DomainAuthenticationFieldType.NAME

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_SHORT)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testLongName() {
        field = "Мишааааааааааааааааааааааааааааааааа"
        authenticationFieldType = DomainAuthenticationFieldType.NAME

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_LONG)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testNameWithSpaces() {
        field = "Иван Иван"
        authenticationFieldType = DomainAuthenticationFieldType.NAME

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testValidEmail() {
        field = "test_email@example.com"
        authenticationFieldType = DomainAuthenticationFieldType.EMAIL

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Success(Unit)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidEmail() {
        field = "test#-example.com"
        authenticationFieldType = DomainAuthenticationFieldType.EMAIL

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testShortEmail() {
        field = "a"
        authenticationFieldType = DomainAuthenticationFieldType.EMAIL

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_SHORT)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testLongEmail() {
        field = "abcdefghijklmnopqrstuvwxyz@gmail.com"
        authenticationFieldType = DomainAuthenticationFieldType.EMAIL

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_LONG)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmailWithSpaces() {
        field = "test @ example.com"
        authenticationFieldType = DomainAuthenticationFieldType.EMAIL

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testValidPassword() {
        field = "12345678A_b-"
        authenticationFieldType = DomainAuthenticationFieldType.PASSWORD

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Success(Unit)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidPassword() {
        field = "Миша!@2012"
        authenticationFieldType = DomainAuthenticationFieldType.PASSWORD

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testShortPassword() {
        field = "1234"
        authenticationFieldType = DomainAuthenticationFieldType.PASSWORD

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_SHORT)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testLongPassword() {
        field = "1111111111111111111111111111111111111111"
        authenticationFieldType = DomainAuthenticationFieldType.PASSWORD

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_LONG)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testPasswordWithSpaces() {
        field = "1234 pass"
        authenticationFieldType = DomainAuthenticationFieldType.PASSWORD

        actual = useCase(field!!, authenticationFieldType!!)
        expected = Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        Assert.assertEquals(actual, expected)
    }
}