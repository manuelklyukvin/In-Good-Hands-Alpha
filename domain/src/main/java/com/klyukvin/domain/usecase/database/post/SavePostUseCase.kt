package com.klyukvin.domain.usecase.database.post

import com.klyukvin.domain.repository.PostRepository
import com.klyukvin.domain.usecase.GetCurrentDateUseCase
import com.klyukvin.domain.usecase.database.image.ConvertToCompressedImageBytesUseCase
import com.klyukvin.domain.usecase.database.image.GetImageLinkByNameUseCase
import com.klyukvin.domain.usecase.database.image.SaveImageUseCase
import com.klyukvin.domain.usecase.database.post.mapper.DomainPostMapper
import com.klyukvin.domain.usecase.database.post.model.DomainInitialPostParameters
import com.klyukvin.domain.usecase.database.user.GetCurrentUserUseCase

class SavePostUseCase(
    private val postRepository: PostRepository,
    private val generatePostIdUseCase: GeneratePostIdUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val convertToCompressedImageBytesUseCase: ConvertToCompressedImageBytesUseCase,
    private val saveImageUseCase: SaveImageUseCase,
    private val getImageLinkByNameUseCase: GetImageLinkByNameUseCase,
    private val domainPostMapper: DomainPostMapper,
    private val getCurrentDateUseCase: GetCurrentDateUseCase
) {

    suspend operator fun invoke(initialPostParameters: DomainInitialPostParameters) {
        val imagesLinks = mutableListOf<String>()
        convertToCompressedImageBytesUseCase(initialPostParameters.imageStrings).collect { compressedImageBytes ->
            saveImageUseCase(compressedImageBytes)
            imagesLinks.add(getImageLinkByNameUseCase(compressedImageBytes.name))
        }

        val postId = generatePostIdUseCase()
        val userId = getCurrentUserUseCase()?.id.orEmpty()
        val date = getCurrentDateUseCase()
        val finalPostParameters = domainPostMapper.mapToFinalPostParameters(
            initialPostParameters = initialPostParameters,
            id = postId,
            userId = userId,
            date = date,
            imageLinks = imagesLinks
        )
        postRepository.savePost(finalPostParameters)
    }
}