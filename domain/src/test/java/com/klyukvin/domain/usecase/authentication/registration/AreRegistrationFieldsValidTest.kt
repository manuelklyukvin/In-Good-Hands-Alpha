package com.klyukvin.domain.usecase.authentication.registration

import com.klyukvin.domain.usecase.authentication.registration.model.DomainRegistrationErrors
import org.junit.Assert
import org.junit.Test

class AreRegistrationFieldsValidTest {

    private val useCase = AreRegistrationFieldsValidUseCase()

    private var fields: DomainRegistrationErrors? = null

    private var actual: Boolean? = null
    private var expected: Boolean? = null

    @Test
    fun testValidFields() {
        fields = DomainRegistrationErrors(
            usernameError = null,
            firstnameError = null,
            lastnameError = null,
            emailError = null,
            passwordError = null,
            confirmPasswordError = null
        )

        actual = useCase(fields!!)
        expected = true
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testOneValidField() {
        fields = DomainRegistrationErrors(
            usernameError = null,
            firstnameError = "Some error",
            lastnameError = "Some error",
            emailError = "Some error",
            passwordError = "Some error",
            confirmPasswordError = "Some error"
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testOneInvalidField() {
        fields = DomainRegistrationErrors(
            usernameError = "Some error",
            firstnameError = null,
            lastnameError = null,
            emailError = null,
            passwordError = null,
            confirmPasswordError = null
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidFields() {
        fields = DomainRegistrationErrors(
            usernameError = "Some error",
            firstnameError = "Some error",
            lastnameError = "Some error",
            emailError = "Some error",
            passwordError = "Some error",
            confirmPasswordError = "Some error"
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }
}