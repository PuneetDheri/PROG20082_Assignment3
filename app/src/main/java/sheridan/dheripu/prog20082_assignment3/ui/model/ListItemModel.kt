package sheridan.dheripu.prog20082_assignment3.ui.model


import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.domain.Item
import sheridan.dheripu.prog20082_assignment3.ui.common.formatCurrency

data class ListItemModel(
    val id: Int,
    val name: String,
    val price: String,
    val priority: Priority,
    val selected: Boolean
){
    constructor(item: Item): this(
        id = item.id,
        name = item.name,
        price = formatCurrency(item.price),
        priority = item.priority,
        selected = item.selected
    )

    constructor(): this(Item())
}

fun Item.toListItemModel() = ListItemModel(this)


