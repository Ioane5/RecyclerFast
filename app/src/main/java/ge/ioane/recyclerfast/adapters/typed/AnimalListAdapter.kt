package ge.ioane.recyclerfast.adapters.typed

import androidx.viewbinding.ViewBinding
import coil.load
import ge.ioane.recyclerfast.AnimalListItemUiEntity
import ge.ioane.recyclerfast.AnimalListItemUiEntity.*
import ge.ioane.recyclerfast.databinding.CatListItemBinding
import ge.ioane.recyclerfast.databinding.DogListItemBinding
import ge.ioane.recyclerfast.databinding.DolphinListItemBinding

fun List<AnimalListItemUiEntity>.toListUiEntities(): List<ListItemUiEntity<AnimalListItemUiEntity, ViewBinding>> {
    return this.map { it.toListItemUiEntity() }
}

fun AnimalListItemUiEntity.toListItemUiEntity(): ListItemUiEntity<AnimalListItemUiEntity, ViewBinding> {
    return when (this) {
        is Dog -> createForViewBinding(this, id, DogListItemBinding::inflate, ::bindDogLayout)
        is Cat -> createForViewBinding(this, id, CatListItemBinding::inflate, ::bindCatLayout)
        is Dolphin ->createForViewBinding(this, id, DolphinListItemBinding::inflate, ::bindDolphinLayout)
    }
}

private fun bindDogLayout(binding: DogListItemBinding, item: Dog) {
    binding.dogImage.load(item.image) {
        crossfade(true)
    }
    binding.dogTitle.text = item.name
    binding.dogBarkingLevelValue.text = item.barkLevel.toString()
}

private fun bindCatLayout(binding: CatListItemBinding, item: Cat) {
    binding.catImage.load(item.image) {
        crossfade(true)
    }
    binding.catTitle.text = item.name
    binding.catMewLevelValue.text = item.mewLevel.toString()
}

private fun bindDolphinLayout(binding: DolphinListItemBinding, item: Dolphin) {
    binding.dolphinImage.load(item.image) {
        crossfade(true)
    }
    binding.dolphinTitle.text = item.name
    binding.dolphinSwimmingSpeedValue.text = item.swimmingSpeed.toString()
}

