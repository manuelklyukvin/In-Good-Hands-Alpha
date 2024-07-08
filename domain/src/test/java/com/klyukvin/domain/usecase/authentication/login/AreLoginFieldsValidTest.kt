package com.klyukvin.domain.usecase.authentication.login

import com.klyukvin.domain.usecase.authentication.login.model.DomainLoginErrors
import org.junit.Assert
import org.junit.Test

class AreLoginFieldsValidTest {

    private val useCase = AreLoginFieldsValidUseCase()

    private var errors: DomainLoginErrors? = null

    private var actual: Boolean? = null
    private var expected: Boolean? = null

    @Test
    fun testValidFields() {
        errors = DomainLoginErrors(
            emailError = null,
            passwordError = null
        )

        actual = useCase(errors!!)
        expected = true
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidEmail() {
        errors = DomainLoginErrors(
            emailError = "Some error",
            passwordError = null
        )

        actual = useCase(errors!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidPassword() {
        errors = DomainLoginErrors(
            emailError = null,
            passwordError = "Some error"
        )

        actual = useCase(errors!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidFields() {
        errors = DomainLoginErrors(
            emailError = "Some error",
            passwordError = "Some error"
        )

        actual = useCase(errors!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }
}