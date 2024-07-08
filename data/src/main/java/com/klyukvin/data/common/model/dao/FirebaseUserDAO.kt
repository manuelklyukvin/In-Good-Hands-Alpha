package com.klyukvin.data.common.model.dao

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.klyukvin.data.common.util.CommonReferences
import com.klyukvin.data.usecase.database.model.DomainSnapshotImpl
import com.klyukvin.domain.common.model.DomainUser
import com.klyukvin.domain.common.model.dao.UserDAO
import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.database.user.mapper.DomainUserMapper
import com.klyukvin.domain.usecase.database.user.model.DomainLoginUserData
import com.klyukvin.domain.usecase.database.user.model.DomainRegistrationUserData
import com.klyukvin.domain.usecase.database.user.model.DomainUserErrorType
import kotlinx.coroutines.tasks.await

class FirebaseUserDAO(
    private val domainUserMapper: DomainUserMapper
) : UserDAO {

    private val auth by lazy {
        Firebase.auth
    }
    private val userDatabase by lazy {
        Firebase.database.getReference(CommonReferences.UserDatabase.reference)
    }

    override suspend fun getCurrentUser(): DomainUser? {
        val userId = auth.currentUser?.uid

        if (userId != null) {
            return getUserById(userId)
        }
        return null
    }

    override suspend fun getUserById(userId: String): DomainUser {
        val dataSnapshot = userDatabase.child(userId).get().await()
        val userSnapshot = DomainSnapshotImpl(dataSnapshot)
        return domainUserMapper.mapToUser(userSnapshot)
    }

    override suspend fun loginUser(loginUserData: DomainLoginUserData): Result<Unit, DomainUserErrorType> {
        try {
            auth.signInWithEmailAndPassword(loginUserData.email, loginUserData.password).await()
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(DomainUserErrorType.COULD_NOT_LOGIN)
        }
    }

    override suspend fun logoutUser() {
        if (getCurrentUser() != null) {
            auth.signOut()
        }
    }

    override suspend fun registerUser(registrationUserData: DomainRegistrationUserData) {
        val result = auth.createUserWithEmailAndPassword(
            registrationUserData.email,
            registrationUserData.password
        ).await()

        val userId = result.user?.uid
        if (userId != null) {
            saveUser(registrationUserData, userId)
        }
    }

    private suspend fun saveUser(
        registrationUserData: DomainRegistrationUserData,
        userId: String
    ) {
        val finalUserParameters = domainUserMapper.mapToFinalUserParameters(
            registrationUserData = registrationUserData,
            userId = userId
        )
        userDatabase.child(userId).setValue(finalUserParameters).await()
    }
}