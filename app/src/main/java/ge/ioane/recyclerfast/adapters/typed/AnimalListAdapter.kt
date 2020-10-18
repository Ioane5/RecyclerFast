package ge.ioane.recyclerfast.adapters.typed

import android.view.View
import coil.load
import ge.ioane.recyclerfast.AnimalListItemUiEntity
import ge.ioane.recyclerfast.AnimalListItemUiEntity.*
import ge.ioane.recyclerfast.R
import kotlinx.android.synthetic.main.cat_list_item.view.*
import kotlinx.android.synthetic.main.dog_list_item.view.*
import kotlinx.android.synthetic.main.dolphin_list_item.view.*

fun List<AnimalListItemUiEntity>.toListUiEntities(): List<ListItemUiEntity<AnimalListItemUiEntity>> {
    return this.map { it.toListItemUiEntity() }
}

fun AnimalListItemUiEntity.toListItemUiEntity(): ListItemUiEntity<AnimalListItemUiEntity> {
    return when (this) {
        is Dog -> createListItemEntity(this, id, R.layout.dog_list_item, ::bindDogLayout)
        is Cat -> createListItemEntity(this, id, R.layout.cat_list_item, ::bindCatLayout)
        is Dolphin -> createListItemEntity(this, id, R.layout.dolphin_list_item, ::bindDolphinLayout)
    }
}

private fun bindDogLayout(view: View, item: Dog) {
    view.dog_image.load(item.image) {
        crossfade(true)
    }
    view.dog_title.text = item.name
    view.dog_barking_level_value.text = item.barkLevel.toString()
}

private fun bindCatLayout(view: View, item: Cat) {
    view.cat_image.load(item.image) {
        crossfade(true)
    }
    view.cat_title.text = item.name
    view.cat_mew_level_value.text = item.mewLevel.toString()
}

private fun bindDolphinLayout(view: View, item: Dolphin) {
    view.dolphin_image.load(item.image) {
        crossfade(true)
    }
    view.dolphin_title.text = item.name
    view.dolphin_swimming_speed_value.text = item.swimmingSpeed.toString()
}

