package com.klyukvin.domain.usecase.authentication.login

import com.klyukvin.domain.usecase.authentication.login.model.DomainLoginFields
import org.junit.Assert
import org.junit.Test

class AreLoginFieldsNotEmptyTest {

    private val useCase = AreLoginFieldsNotEmptyUseCase()

    private var fields: DomainLoginFields? = null

    private var actual: Boolean? = null
    private var expected: Boolean? = null

    @Test
    fun testFilledFields() {
        fields = DomainLoginFields(
            email = "email",
            password = "password"
        )

        actual = useCase(fields!!)
        expected = true
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmptyEmail() {
        fields = DomainLoginFields(
            email = "",
            password = "password"
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmptyPassword() {
        fields = DomainLoginFields(
            email = "email",
            password = ""
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmptyFields() {
        fields = DomainLoginFields(
            email = "",
            password = ""
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }
}