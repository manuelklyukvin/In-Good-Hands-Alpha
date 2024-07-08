package com.klyukvin.domain.usecase.post_creator

import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorCategory
import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorFields
import org.junit.Assert
import org.junit.Test

class ArePostCreatorFieldsNotEmptyTest {

    private val useCase = ArePostCreatorFieldsNotEmptyUseCase()

    private var fields: DomainPostCreatorFields? = null

    private var actual: Boolean? = null
    private var expected: Boolean? = null

    @Test
    fun testFilledFields() {
        fields = DomainPostCreatorFields(
            imageStrings = listOf("image"),
            title = "title",
            description = "description",
            category = DomainPostCreatorCategory.OTHER,
            phoneNumber = "phoneNumber",
            address = "address"
        )

        actual = useCase(fields!!)
        expected = true
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testOneFilledField() {
        fields = DomainPostCreatorFields(
            imageStrings = emptyList(),
            title = "title",
            description = "",
            category = DomainPostCreatorCategory.NOT_SELECTED,
            phoneNumber = "",
            address = ""
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testOneNotFilledField() {
        fields = DomainPostCreatorFields(
            imageStrings = listOf("image"),
            title = "",
            description = "description",
            category = DomainPostCreatorCategory.OTHER,
            phoneNumber = "phoneNumber",
            address = "address"
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testImagesAreNotFilled() {
        fields = DomainPostCreatorFields(
            imageStrings = emptyList(),
            title = "title",
            description = "description",
            category = DomainPostCreatorCategory.OTHER,
            phoneNumber = "phoneNumber",
            address = "address"
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testCategoryIsNotSelected() {
        fields = DomainPostCreatorFields(
            imageStrings = listOf("image"),
            title = "title",
            description = "description",
            category = DomainPostCreatorCategory.NOT_SELECTED,
            phoneNumber = "phoneNumber",
            address = "address"
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEmptyFields() {
        fields = DomainPostCreatorFields(
            imageStrings = emptyList(),
            title = "",
            description = "",
            category = DomainPostCreatorCategory.NOT_SELECTED,
            phoneNumber = "",
            address = ""
        )

        actual = useCase(fields!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }
}