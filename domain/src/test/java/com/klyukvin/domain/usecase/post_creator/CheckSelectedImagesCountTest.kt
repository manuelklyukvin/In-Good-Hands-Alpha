package com.klyukvin.domain.usecase.post_creator

import org.junit.Assert
import org.junit.Test

class CheckSelectedImagesCountTest {

    private val useCase = CheckSelectedImagesCountUseCase()

    private var count: Int? = null

    private var actual: Boolean? = null
    private var expected: Boolean? = null

    @Test
    fun testZeroCount() {
        count = 0

        actual = useCase(count!!)
        expected = true
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testSmallerCount() {
        count = 1

        actual = useCase(count!!)
        expected = true
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testEdgeCount() {
        count = 5

        actual = useCase(count!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testBiggerCount() {
        count = 6

        actual = useCase(count!!)
        expected = false
        Assert.assertEquals(actual, expected)
    }
}