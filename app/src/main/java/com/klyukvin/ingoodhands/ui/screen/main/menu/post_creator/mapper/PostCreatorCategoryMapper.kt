package com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.mapper

import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorCategory
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model.UiPostCreatorCategory

class PostCreatorCategoryMapper {
    
    fun mapToDomain(category: UiPostCreatorCategory): DomainPostCreatorCategory {
        return when (category) {
            UiPostCreatorCategory.Cats -> DomainPostCreatorCategory.CATS
            UiPostCreatorCategory.Dogs -> DomainPostCreatorCategory.DOGS
            UiPostCreatorCategory.Rodents -> DomainPostCreatorCategory.RODENTS
            UiPostCreatorCategory.Birds -> DomainPostCreatorCategory.BIRDS
            UiPostCreatorCategory.Fishes -> DomainPostCreatorCategory.FISHES
            UiPostCreatorCategory.Reptiles -> DomainPostCreatorCategory.REPTILES
            UiPostCreatorCategory.Other -> DomainPostCreatorCategory.OTHER
            UiPostCreatorCategory.NotSelected -> DomainPostCreatorCategory.NOT_SELECTED
        }
    }
}