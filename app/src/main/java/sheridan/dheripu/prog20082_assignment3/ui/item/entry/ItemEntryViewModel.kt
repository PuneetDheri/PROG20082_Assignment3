package sheridan.dheripu.prog20082_assignment3.ui.item.entry

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sheridan.dheripu.prog20082_assignment3.data.repository.ItemsRepository
import sheridan.dheripu.prog20082_assignment3.ui.item.form.FormViewModel

import javax.inject.Inject

/**
 * ViewModel to validate and insert items in the Room database.
 */
@HiltViewModel
class ItemEntryViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository
) : FormViewModel() {

    fun saveItem() = viewModelScope.launch{
        if (uiState.itemFormModel.isValid()) {
            itemsRepository.insertItem(uiState.itemFormModel.toItem())
        }
    }

}

