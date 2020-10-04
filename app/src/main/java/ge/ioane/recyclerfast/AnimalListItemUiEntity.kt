package ge.ioane.recyclerfast

import android.net.Uri

sealed class AnimalListItemUiEntity {
    abstract val id: String
    abstract val name: String
    abstract val image: Uri?

    data class Dog(
        override val id: String,
        override val name: String,
        override val image: Uri?,
        val barkLevel: Int
    ) : AnimalListItemUiEntity()

    data class Cat(
        override val id: String,
        override val name: String,
        override val image: Uri?,
        val mewLevel: Int
    ) : AnimalListItemUiEntity()

    data class Dolphin(
        override val id: String,
        override val name: String,
        override val image: Uri?,
        val swimmingSpeed: Int
    ) : AnimalListItemUiEntity()
}