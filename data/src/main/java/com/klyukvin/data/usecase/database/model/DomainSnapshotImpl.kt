package com.klyukvin.data.usecase.database.model

import com.google.firebase.database.DataSnapshot
import com.klyukvin.domain.usecase.database.model.DomainSnapshot

class DomainSnapshotImpl(
    private val dataSnapshot: DataSnapshot
) : DomainSnapshot {

    override fun <T> getValue(reference: String, valueType: Class<T>): T? {
        return dataSnapshot.child(reference).getValue(valueType)
    }

    override fun getStringValue(reference: String): String {
        return dataSnapshot.child(reference).getValue(String::class.java).orEmpty()
    }

    override fun getStringValues(reference: String): List<String> {
        return dataSnapshot.child(reference).children.map { snapshot ->
            snapshot.getValue(String::class.java).orEmpty()
        }
    }
}