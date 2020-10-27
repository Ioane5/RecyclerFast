package ge.ioane.recyclerfast.adapters.typed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

/**
 * Used for binding with ViewBinding Library
 * -- Example
 * -
 * - createForViewBinding(this, id, DogListItemBinding::inflate) {
 * -  binding.dogImage.load(item.image)
 * -  binding.dogTitle.text = item.name
 * -  binding.dogBarkingLevelValue.text = item.barkLevel.toString()
 * - }
 */
fun <T : Equitable, B : ViewBinding> createForViewBinding(
    item: T,
    key: String,
    createViewBinding: (layoutInflater: LayoutInflater, viewGroup: ViewGroup, attachToParent: Boolean) -> B,
    bindLayout: (binding: B, item: T) -> Unit
): ListItemUiEntity<T, B> = object : ListItemUiEntity<T, B> {
    override val item: T = item
    override val key: String = key

    override fun createViewHolder(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup
    ): TypeBasedAdapter.TypeBasedAdapterViewHolder<B> {
        val viewBinding = createViewBinding(layoutInflater, viewGroup, false)
        return TypeBasedAdapter.TypeBasedAdapterViewHolder(viewBinding, viewBinding.root)
    }

    override fun bindViewHolder(viewHolder: TypeBasedAdapter.TypeBasedAdapterViewHolder<B>) {
        bindLayout(viewHolder.binding, item)
    }
}

/**
 * Used for building manual binding.
  * -- Example
  * -
  * - createForManualBinding(dog, dog.id, R.layout.dog_list_item, { view ->
  * -     object {
  * -         val title = view.findViewById<TextView>(R.id.dog_title)
  * -         val image = view.findViewById<ImageView>(R.id.dog_image)
  * -         val barkingLevel = view.findViewById<TextView>(R.id.dog_barking_level_value)
  * -     }
  * - }, { binding, item ->
  * -     binding.title.text = item.name
  * -     binding.image.load(item.image)
  * -     binding.barkingLevel.text = item.barkLevel
  * - })
*/
fun <T : Equitable, B : Any> createForManualBinding(
    item: T,
    key: String,
    @LayoutRes layoutResId: Int,
    createBinding: (View) -> B,
    bindLayout: (binding: B, item: T) -> Unit
) = object : ListItemUiEntity<T, B> {
    override val item: T = item
    override val key: String = key

    override fun createViewHolder(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup
    ): TypeBasedAdapter.TypeBasedAdapterViewHolder<B> {
        val view = layoutInflater.inflate(layoutResId, viewGroup, false)
        val binding = createBinding(view)
        return TypeBasedAdapter.TypeBasedAdapterViewHolder(binding, view)
    }

    override fun bindViewHolder(viewHolder: TypeBasedAdapter.TypeBasedAdapterViewHolder<B>) {
        bindLayout(viewHolder.binding, item)
    }
}