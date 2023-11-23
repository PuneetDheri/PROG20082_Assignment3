package sheridan.dheripu.prog20082_assignment3.ui.item.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import sheridan.dheripu.prog20082_assignment3.data.repository.ItemsRepository
import sheridan.dheripu.prog20082_assignment3.ui.item.form.FormViewModel
import sheridan.dheripu.prog20082_assignment3.ui.item.form.toItemFormUiState
import sheridan.dheripu.prog20082_assignment3.ui.model.ItemFormModel
import sheridan.dheripu.prog20082_assignment3.ui.navigation.ItemEditDestination
import javax.inject.Inject

/**
 * ViewModel to retrieve and update an item from the [ItemsRepository]'s data source.
 */
@HiltViewModel
class ItemEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : FormViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            uiState = itemsRepository.getItemByIdStream(itemId)
                .filterNotNull()
                .first()
                .toItemFormUiState(isEntryValid = true)
        }
    }

    /**
     * Update the item in the [ItemsRepository]'s data source
     */
    fun updateItem() = viewModelScope.launch {
        val formData: ItemFormModel = uiState.itemFormModel
        if (formData.isValid()) {
            itemsRepository.updateItem(formData.toItem())
        }
    }
}
