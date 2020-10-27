package ge.ioane.recyclerfast.adapters.typed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ge.ioane.recyclerfast.adapters.typed.TypeBasedAdapter.TypeBasedAdapterViewHolder

class TypeBasedAdapter<ItemType : Equitable, BindingType : Any> :
    ListAdapter<ListItemUiEntity<ItemType, BindingType>, TypeBasedAdapterViewHolder<BindingType>>(
        TypeBasedAdapterDiffUtilsCallback<ListItemUiEntity<ItemType, BindingType>>()
    ) {

    private val viewTypeViewHolderCreatorMap =
        mutableMapOf<Int, (LayoutInflater, ViewGroup) -> TypeBasedAdapterViewHolder<BindingType>>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypeBasedAdapterViewHolder<BindingType> {
        val inflater = LayoutInflater.from(parent.context)
        return viewTypeViewHolderCreatorMap.getValue(viewType)(inflater, parent)
    }

    override fun onBindViewHolder(holder: TypeBasedAdapterViewHolder<BindingType>, position: Int) {
        getItem(position).bindViewHolder(holder)
    }

    /**
     * Return layout resId
     */
    override fun getItemViewType(position: Int): Int {
        val viewType = getItem(position).item::class.hashCode()
        viewTypeViewHolderCreatorMap[viewType] = getItem(position)::createViewHolder
        return viewType
    }

    class TypeBasedAdapterViewHolder<out BindingType : Any>(
        val binding: BindingType, view: View
    ) : RecyclerView.ViewHolder(view)
}

interface Equitable {
    override fun equals(other: Any?): Boolean
}

interface ListItemUiEntity<out T : Equitable, out B : Any> {

    val item: T

    /**
     * Item unique key, which must stay same if update happens on same item, so that
     * recyclerview rebinds same item with new data.
     */
    val key: String

    fun createViewHolder(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup
    ): TypeBasedAdapterViewHolder<B>

    /**
     * TODO: Can we deal with unsafe variance?
     */
    fun bindViewHolder(viewHolder: TypeBasedAdapterViewHolder<@UnsafeVariance B>)
}

class TypeBasedAdapterDiffUtilsCallback<T : ListItemUiEntity<*, *>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.item == newItem.item
    }
}