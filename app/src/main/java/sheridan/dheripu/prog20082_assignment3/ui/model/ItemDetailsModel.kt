package sheridan.dheripu.prog20082_assignment3.ui.model

import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.domain.Item
import sheridan.dheripu.prog20082_assignment3.ui.common.formatCurrency

data class ItemDetailsModel(
    val id: Int,
    val name: String,
    val priority: Priority
){
    constructor(item: Item): this(
        id = item.id,
        name = item.name,
        priority = item.priority
    )

    constructor(): this(Item())
}

fun Item.toItemDetailsModel() = ItemDetailsModel(this)


