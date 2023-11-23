package sheridan.dheripu.prog20082_assignment3.ui.item.details

import sheridan.dheripu.prog20082_assignment3.data.repository.ItemsRepository
import sheridan.dheripu.prog20082_assignment3.ui.navigation.ItemDetailsDestination


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to retrieve, update and delete an item from the [ItemsRepository]'s data source.
 */
@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository,
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    /**
     * Holds the item details ui state. The data is retrieved from [ItemsRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<ItemDetailsUiState> =
        itemsRepository.getItemByIdStream(itemId)
            .filterNotNull()
            .map { item ->
                ItemDetailsUiState(item)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    /**
     * Reduces the item quantity by one and update the [ItemsRepository]'s data source.
     */


    /**
     * Deletes the item from the [ItemsRepository]'s data source.
     */
    fun deleteItem() = viewModelScope.launch{
        itemsRepository.deleteItemById(uiState.value.item.id)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

