package ge.ioane.recyclerfast

import androidx.core.net.toUri
import ge.ioane.recyclerfast.AnimalListItemUiEntity.*
import java.util.*

object AnimalListTestData {

    private fun randomId(): String = UUID.randomUUID().toString()

    private val dogItems = listOf(
        Dog(
            id = randomId(),
            name = "Rothweiler",
            image = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/Rottweiler_standing_facing_left.jpg/330px-Rottweiler_standing_facing_left.jpg".toUri(),
            barkLevel = 10
        ),
        Dog(
            id = randomId(),
            name = "Labrador Retriever",
            image = "https://upload.wikimedia.org/wikipedia/commons/3/34/Labrador_on_Quantock_%282175262184%29.jpg".toUri(),
            barkLevel = 5
        ),
        Dog(
            id = randomId(),
            name = "Alaskan Malamute",
            image = "https://upload.wikimedia.org/wikipedia/commons/9/9f/Alaskan_Malamute.jpg".toUri(),
            barkLevel = 9
        ),
        Dog(
            id = randomId(),
            name = "Dachshund",
            image = "https://upload.wikimedia.org/wikipedia/commons/a/a8/Mao-the-dachshund-wikipedia.png".toUri(),
            barkLevel = 2
        )
    )

    private val dolphinItems = listOf(
        Dolphin(
            id = randomId(),
            name = "Short-beaked common dolphin",
            image = "https://upload.wikimedia.org/wikipedia/commons/5/52/Delphinus_delphis_with_calf.jpg".toUri(),
            swimmingSpeed = 7
        ),
        Dolphin(
            id = randomId(),
            name = "Indo-Pacific bottlenose dolphin",
            image = "https://upload.wikimedia.org/wikipedia/commons/8/87/Tursiops_aduncus%2C_Port_River%2C_Adelaide%2C_Australia_-_2003.jpg".toUri(),
            swimmingSpeed = 8
        )
    )

    private val catItems = listOf(
        Cat(
            id = randomId(),
            name = "British Shorthair",
            image = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Britishblue.jpg/330px-Britishblue.jpg".toUri(),
            mewLevel = 5
        ),
        Cat(
            id = randomId(),
            name = "Cornish Rex",
            image = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Rex_staredown.jpg/1024px-Rex_staredown.jpg".toUri(),
            mewLevel = 8
        ),
        Cat(
            id = randomId(),
            name = "Snowshoe cat",
            image = "https://upload.wikimedia.org/wikipedia/commons/2/26/Snowshoe_Siamese_Kitten.jpg".toUri(),
            mewLevel = 4
        )
    )

    val animalItems = (dogItems + catItems + dolphinItems).shuffled()
}