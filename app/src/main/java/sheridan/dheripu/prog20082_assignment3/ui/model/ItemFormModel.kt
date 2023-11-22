package sheridan.dheripu.prog20082_assignment3.ui.model
import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.domain.Item
data class ItemFormModel(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val priority: Priority = Priority.LOW,

    ) {
    fun isValid(): Boolean =
        name.isNotBlank() && price.isNotBlank()

    fun toItem(): Item = Item(
        id = id,
        name = name,
        price = price.toDoubleOrNull() ?: 0.0,
        priority = priority
    )
}

fun Item.toItemFormData(): ItemFormModel = ItemFormModel(
    id = id,
    name = name,
    price = price.toString(),
    priority = priority
)
