package com.klyukvin.data.common.model.dao

import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.klyukvin.data.common.util.CommonReferences
import com.klyukvin.domain.common.model.dao.ImageDAO
import com.klyukvin.domain.usecase.database.image.model.DomainCompressedImageBytes
import kotlinx.coroutines.tasks.await

class FirebaseImageDAO : ImageDAO {

    private val imageStorage by lazy {
        Firebase.storage.getReference(CommonReferences.ImageStorage.reference)
    }

    override suspend fun saveImage(compressedImageBytes: DomainCompressedImageBytes) {
        imageStorage.child(compressedImageBytes.name).putBytes(compressedImageBytes.imageBytes).await()
    }

    override suspend fun getImageLinkByName(imageName: String): String {
        return imageStorage.child(imageName).downloadUrl.await().toString()
    }
}