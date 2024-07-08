package com.klyukvin.domain.usecase.post_creator

import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorErrors
import org.junit.Assert
import org.junit.Test

class ArePostCreatorFieldsValidTest {

    private val useCase = ArePostCreatorFieldsValidUseCase()

    private var fields: DomainPostCreatorErrors? = null

    private var actual: Boolean? = null
    private var expected: Boolean? = null

    @Test
    fun testValidFields() {
        fields = DomainPostCreatorErrors(
            imagesError = null,
            titleError = null,
            descriptionError = null,
            categoryError = null,
            phoneNumberError = null,
            addressError = null
        )

        actual = useCase(fields!!)
        expected = true
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testOneValidField() {
        fields = DomainPostCreatorErrors(
            imagesError = null,
            titleError = "Some error",
            descriptionError = "Some error",
            categoryError = "Some error",
            phoneNumberError = "Some error",
            addressError = "Some error"
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testOneInvalidField() {
        fields = DomainPostCreatorErrors(
            imagesError = "Some error",
            titleError = null,
            descriptionError = null,
            categoryError = null,
            phoneNumberError = null,
            addressError = null
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testInvalidFields() {
        fields = DomainPostCreatorErrors(
            imagesError = "Some error",
            titleError = "Some error",
            descriptionError = "Some error",
            categoryError = "Some error",
            phoneNumberError = "Some error",
            addressError = "Some error"
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }
}