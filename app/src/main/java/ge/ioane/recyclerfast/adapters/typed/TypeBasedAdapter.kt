package ge.ioane.recyclerfast.adapters.typed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ge.ioane.recyclerfast.adapters.typed.TypeBasedAdapter.TypeBasedAdapterViewHolder
import kotlinx.android.extensions.LayoutContainer

class TypeBasedAdapter<ItemType : Equitable> :
    ListAdapter<ListItemUiEntity<ItemType>, TypeBasedAdapterViewHolder>(
        TypeBasedAdapterDiffUtilsCallback<ListItemUiEntity<ItemType>>()
    ) {

    /**
     * ViewType is used as LayoutResId
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        layoutResId: Int
    ): TypeBasedAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return TypeBasedAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeBasedAdapterViewHolder, position: Int) {
        getItem(position).bindLayout(holder.itemView)
    }

    /**
     * Return layout resId
     */
    override fun getItemViewType(position: Int): Int {
        return getItem(position).getLayout().hashCode()
    }

    class TypeBasedAdapterViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer
}

interface Equitable {
    override fun equals(other: Any?): Boolean
}

interface ListItemUiEntity<out T : Equitable> {

    val item: T

    /**
     * Item unique key, which must stay same if update happens on same item, so that
     * recyclerview rebinds same item with new data.
     */
    val key: String

    @LayoutRes
    fun getLayout(): Int

    fun bindLayout(view: View)
}

fun <T : Equitable> createListItemEntity(
    item: T,
    key: String,
    @LayoutRes layout: Int,
    bindLayout: (View, T) -> Unit
): ListItemUiEntity<T> = object : ListItemUiEntity<T> {
    override val item: T = item
    override val key: String = key
    override fun getLayout(): Int = layout
    override fun bindLayout(view: View) = bindLayout(view, item)
}

class TypeBasedAdapterDiffUtilsCallback<T : ListItemUiEntity<*>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.item == newItem.item
    }
}