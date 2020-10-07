package ge.ioane.recyclerfast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ge.ioane.recyclerfast.AnimalListAdapter.AnimalListViewHolder
import ge.ioane.recyclerfast.AnimalListAdapter.AnimalListViewHolder.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cat_list_item.view.*
import kotlinx.android.synthetic.main.dog_list_item.view.*
import kotlinx.android.synthetic.main.dolphin_list_item.view.*

class AnimalListAdapter :
    ListAdapter<AnimalListItemUiEntity, AnimalListViewHolder>(AnimalListDiffCallback()) {

    sealed class AnimalListViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        class DogListViewHolder(view: View) : AnimalListViewHolder(view) {
            fun bind(item: AnimalListItemUiEntity.Dog) {
                containerView.dog_image.load(item.image) {
                    crossfade(true)
                }
                containerView.dog_title.text = item.name
                containerView.dog_barking_level_value.text = item.barkLevel.toString()
            }
        }

        class CatListViewHolder(view: View) : AnimalListViewHolder(view) {
            fun bind(item: AnimalListItemUiEntity.Cat) {
                containerView.cat_image.load(item.image) {
                    crossfade(true)
                }
                containerView.cat_title.text = item.name
                containerView.cat_mew_level_value.text = item.mewLevel.toString()
            }
        }

        class DolphinListViewHolder(view: View) : AnimalListViewHolder(view) {
            fun bind(item: AnimalListItemUiEntity.Dolphin) {
                containerView.dolphin_image.load(item.image) {
                    crossfade(true)
                }
                containerView.dolphin_title.text = item.name
                containerView.dolphin_swimming_speed_value.text = item.swimmingSpeed.toString()            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> {
                val view = inflater.inflate(R.layout.dog_list_item, parent, false)
                DogListViewHolder(view)
            }
            1 -> {
                val view = inflater.inflate(R.layout.cat_list_item, parent, false)
                CatListViewHolder(view)
            }
            2 -> {
                val view = inflater.inflate(R.layout.dolphin_list_item, parent, false)
                DolphinListViewHolder(view)
            }
            else -> error("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: AnimalListViewHolder, position: Int) {
        val item = getItem(position)
        return when (holder) {
            is DogListViewHolder -> holder.bind(item as AnimalListItemUiEntity.Dog)
            is CatListViewHolder -> holder.bind(item as AnimalListItemUiEntity.Cat)
            is DolphinListViewHolder -> holder.bind(item as AnimalListItemUiEntity.Dolphin)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is AnimalListItemUiEntity.Dog -> 0
            is AnimalListItemUiEntity.Cat -> 1
            is AnimalListItemUiEntity.Dolphin -> 2
        }
    }
}

class AnimalListDiffCallback : DiffUtil.ItemCallback<AnimalListItemUiEntity>() {

    override fun areContentsTheSame(
        oldItem: AnimalListItemUiEntity,
        newItem: AnimalListItemUiEntity
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: AnimalListItemUiEntity,
        newItem: AnimalListItemUiEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }
}