package sheridan.dheripu.prog20082_assignment3.ui.item.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.ui.model.ItemFormModel

abstract class FormViewModel(
): ViewModel() {

    var uiState: ItemFormUiState by mutableStateOf(ItemFormUiState())
        protected set


    fun onNameChange(newName: String) =
        updateUiState(uiState.itemFormModel.copy(name = newName))


    fun onPriorityChange(newPriority: Priority) =
        updateUiState(uiState.itemFormModel.copy(priority = newPriority))


    private fun updateUiState(itemFormModel: ItemFormModel) {
        uiState =
            ItemFormUiState(
                itemFormModel = itemFormModel,
                isEntryValid = itemFormModel.isValid()
            )
    }
}