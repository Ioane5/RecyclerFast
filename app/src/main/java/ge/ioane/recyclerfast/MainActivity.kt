package ge.ioane.recyclerfast

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import ge.ioane.recyclerfast.adapters.standard.AnimalListAdapter
import ge.ioane.recyclerfast.adapters.typed.TypeBasedAdapter
import ge.ioane.recyclerfast.adapters.typed.toListUiEntities
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //bindStandardAdapter()
        bindTypeBasedAdapter()
    }

    private fun bindStandardAdapter() {
        val adapter = AnimalListAdapter()
        animal_list_recycler.adapter = adapter
        viewModel.animalListItems.observe(this, adapter::submitList)
    }

    private fun bindTypeBasedAdapter() {
        val adapter = TypeBasedAdapter<AnimalListItemUiEntity, ViewBinding>()
        animal_list_recycler.adapter = adapter
        viewModel.animalListItems.observe(this) {
            adapter.submitList(it.toListUiEntities())
        }
    }
}

