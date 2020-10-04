package ge.ioane.recyclerfast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val animalListItems = MutableLiveData<List<AnimalListItemUiEntity>>()

    init {
        animalListItems.value = AnimalListTestData.animals
    }
}