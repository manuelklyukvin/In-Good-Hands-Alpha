package com.klyukvin.domain.usecase.database.model

interface DomainSnapshot {

    fun <T> getValue(reference: String, valueType: Class<T>): T?

    fun getStringValue(reference: String): String

    fun getStringValues(reference: String): List<String>
}