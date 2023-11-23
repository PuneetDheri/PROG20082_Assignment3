package sheridan.dheripu.prog20082_assignment3.ui.item.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import sheridan.dheripu.prog20082_assignment3.ui.model.ItemFormModel

abstract class FormViewModel(
): ViewModel() {

    var uiState: ItemFormUiState by mutableStateOf(ItemFormUiState())
        protected set


    fun onNameChange(newName: String) =
        updateUiState(uiState.itemFormModel.copy(name = newName))

    fun onPriceChange(newPrice: String) =
        updateUiState(uiState.itemFormModel.copy(price = newPrice))


    private fun updateUiState(itemFormModel: ItemFormModel) {
        uiState =
            ItemFormUiState(
                itemFormModel = itemFormModel,
                isEntryValid = itemFormModel.isValid()
            )
    }
}