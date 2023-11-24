package sheridan.dheripu.prog20082_assignment3.ui.model
import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.domain.Item
data class ItemFormModel(
    val id: Int = 0,
    val name: String = "",
    val priority: Priority = Priority.LOW,

    ) {
    fun isValid(): Boolean =
        name.isNotBlank()

    fun toItem(): Item = Item(
        id = id,
        name = name,
        priority = priority
    )
}

fun Item.toItemFormData(): ItemFormModel = ItemFormModel(
    id = id,
    name = name,
    priority = priority
)
