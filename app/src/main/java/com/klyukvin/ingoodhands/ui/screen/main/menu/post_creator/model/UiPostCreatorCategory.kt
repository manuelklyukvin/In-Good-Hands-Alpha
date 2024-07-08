package com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model

import com.klyukvin.ingoodhands.R

sealed class UiPostCreatorCategory(val nameId: Int) {

    data object Cats : UiPostCreatorCategory(catsNameId)
    data object Dogs : UiPostCreatorCategory(dogsNameId)
    data object Rodents : UiPostCreatorCategory(rodentsNameId)
    data object Birds : UiPostCreatorCategory(birdsNameId)
    data object Fishes : UiPostCreatorCategory(fishesNameId)
    data object Reptiles : UiPostCreatorCategory(reptilesNameId)
    data object Other : UiPostCreatorCategory(otherNameId)
    data object NotSelected : UiPostCreatorCategory(notSelectedNameId)

    private companion object {

        val catsNameId = R.string.category_cats
        val dogsNameId = R.string.category_dogs
        val rodentsNameId = R.string.category_rodents
        val birdsNameId = R.string.category_birds
        val fishesNameId = R.string.category_fishes
        val reptilesNameId = R.string.category_reptiles
        val otherNameId = R.string.category_other
        val notSelectedNameId = R.string.category_not_selected
    }
}