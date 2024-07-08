package com.klyukvin.data.common.model.dao

import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.klyukvin.data.common.util.CommonReferences
import com.klyukvin.data.common.util.PostReferences
import com.klyukvin.data.usecase.database.model.DomainSnapshotImpl
import com.klyukvin.domain.common.model.DomainPost
import com.klyukvin.domain.common.model.dao.PostDAO
import com.klyukvin.domain.usecase.database.post.mapper.DomainPostMapper
import kotlinx.coroutines.tasks.await

class FirebasePostDAO(
    private val domainPostMapper: DomainPostMapper
) : PostDAO {

    private val postDatabase by lazy {
        Firebase.database.getReference(CommonReferences.PostDatabase.reference)
    }

    override suspend fun loadPostsOnPage(
        currentPage: Int,
        pageSize: Int,
        offset: Int
    ): List<DomainPost> {
        val dataSnapshot = postDatabase.orderByKey().limitToLast(offset + pageSize).get().await()
        val postList = dataSnapshot.children.reversed().drop(offset).map { snapshot ->
            val postSnapshot = DomainSnapshotImpl(snapshot)
            domainPostMapper.mapToPost(postSnapshot)
        }
        return postList
    }

    override suspend fun loadPostById(postId: String): DomainPost {
        val dataSnapshot = postDatabase.child(postId).get().await()
        val postSnapshot = DomainSnapshotImpl(dataSnapshot)
        return domainPostMapper.mapToPost(postSnapshot)
    }

    override suspend fun savePost(finalPostParameters: Map<String, Any>) {
        val postId = finalPostParameters[PostReferences.Id.reference].toString()
        postDatabase.child(postId).setValue(finalPostParameters).await()
    }

    override fun generatePostId(): String {
        return postDatabase.push().key.orEmpty()
    }

    override suspend fun getPageCount(pageSize: Int): Int {
        val postCount = postDatabase.get().await().children.count()
        return (postCount + pageSize - 1) / pageSize
    }
}