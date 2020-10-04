package ge.ioane.recyclerfast

import androidx.core.net.toUri
import java.util.*

object AnimalListTestData {

    private fun randomId(): String = UUID.randomUUID().toString()

    val animals = listOf<AnimalListItemUiEntity>(
        AnimalListItemUiEntity.Dog(
            id = randomId(),
            name = "Rothweiler",
            image = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/Rottweiler_standing_facing_left.jpg/330px-Rottweiler_standing_facing_left.jpg".toUri(),
            barkLevel = 10
        ),
        AnimalListItemUiEntity.Dog(
            id = randomId(),
            name = "Bulldog",
            image = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Bulldog_adult_male.jpg/220px-Bulldog_adult_male.jpg".toUri(),
            barkLevel = 7
        ),
        AnimalListItemUiEntity.Dog(
            id = randomId(),
            name = "Poodle",
            image = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Full_attention_%288067543690%29.jpg/1280px-Full_attention_%288067543690%29.jpg".toUri(),
            barkLevel = 3
        ),
        AnimalListItemUiEntity.Dog(
            id = randomId(),
            name = "Labrador Retriever",
            image = "https://upload.wikimedia.org/wikipedia/commons/3/34/Labrador_on_Quantock_%282175262184%29.jpg".toUri(),
            barkLevel = 5
        ),
        AnimalListItemUiEntity.Dog(
            id = randomId(),
            name = "Alaskan Malamute",
            image = "https://upload.wikimedia.org/wikipedia/commons/9/9f/Alaskan_Malamute.jpg".toUri(),
            barkLevel = 9
        ),
        AnimalListItemUiEntity.Dog(
            id = randomId(),
            name = "Dachshund",
            image = "https://upload.wikimedia.org/wikipedia/commons/a/a8/Mao-the-dachshund-wikipedia.png".toUri(),
            barkLevel = 2
        )
    )
}