package sheridan.dheripu.prog20082_assignment3.ui.item.form

import sheridan.dheripu.prog20082_assignment3.domain.Item
import sheridan.dheripu.prog20082_assignment3.ui.model.ItemFormModel
import sheridan.dheripu.prog20082_assignment3.ui.model.toItemFormData

/**
 * Represents Ui State for an Item.
 */
data class ItemFormUiState(
    val itemFormModel: ItemFormModel = ItemFormModel(),
    val isEntryValid: Boolean = false
)

fun Item.toItemFormUiState(isEntryValid: Boolean = false): ItemFormUiState =
    ItemFormUiState(
        itemFormModel = this.toItemFormData(),
        isEntryValid = isEntryValid
    )