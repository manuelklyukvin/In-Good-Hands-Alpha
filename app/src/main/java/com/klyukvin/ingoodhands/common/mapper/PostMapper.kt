package com.klyukvin.ingoodhands.common.mapper

import android.net.Uri
import com.klyukvin.domain.common.model.DomainPost
import com.klyukvin.domain.usecase.database.user.GetUserByIdUseCase
import com.klyukvin.ingoodhands.common.model.UiPost

class PostMapper(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val userMapper: UserMapper
) {

    suspend fun mapToUi(post: DomainPost): UiPost {
        with(post) {
            return UiPost(
                id = id,
                user = userMapper.mapToUi(getUserByIdUseCase(userId)),
                imageUris = imageStrings.map {
                    Uri.parse(it)
                },
                title = title,
                description = description,
                category = category,
                phoneNumber = phoneNumber,
                address = address,
                date = date
            )
        }
    }
}