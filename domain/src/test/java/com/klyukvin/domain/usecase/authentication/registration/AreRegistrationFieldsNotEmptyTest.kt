package com.klyukvin.domain.usecase.authentication.registration

import com.klyukvin.domain.usecase.authentication.registration.model.DomainRegistrationFields
import org.junit.Assert
import org.junit.Test

class AreRegistrationFieldsNotEmptyTest {

    private val useCase = AreRegistrationFieldsNotEmptyUseCase()

    private var fields: DomainRegistrationFields? = null

    private var actual: Boolean? = null
    private var expected: Boolean? = null

    @Test
    fun testFilledFields() {
        fields = DomainRegistrationFields(
            username = "username",
            firstname = "firstname",
            lastname = "lastname",
            email = "email",
            password = "password",
            confirmPassword = "confirmPassword"
        )

        actual = useCase(fields!!)
        expected = true
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testOneFilledField() {
        fields = DomainRegistrationFields(
            username = "username",
            firstname = "",
            lastname = "",
            email = "",
            password = "",
            confirmPassword = ""
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testOneNotFilledField() {
        fields = DomainRegistrationFields(
            username = "",
            firstname = "firstname",
            lastname = "lastname",
            email = "email",
            password = "password",
            confirmPassword = "confirmPassword"
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmptyFields() {
        fields = DomainRegistrationFields(
            username = "",
            firstname = "",
            lastname = "",
            email = "",
            password = "",
            confirmPassword = ""
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }
}