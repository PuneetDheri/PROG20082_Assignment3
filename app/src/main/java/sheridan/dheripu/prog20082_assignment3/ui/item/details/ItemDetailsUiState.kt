package sheridan.dheripu.prog20082_assignment3.ui.item.details

import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.domain.Item
import sheridan.dheripu.prog20082_assignment3.ui.model.ItemDetailsModel
import sheridan.dheripu.prog20082_assignment3.ui.model.toItemDetailsModel

data class ItemDetailsUiState(
    val priority: Priority,
    val item: ItemDetailsModel
){
    constructor(item: Item): this (
        priority  = Priority.LOW,
        item = item.toItemDetailsModel()
    )

    constructor(): this(Item())
}