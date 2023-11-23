package sheridan.dheripu.prog20082_assignment3.ui.item.entry

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.ui.common.ShoppingListTopAppBar
import sheridan.dheripu.prog20082_assignment3.ui.item.form.ItemFormBody
import sheridan.dheripu.prog20082_assignment3.ui.model.ItemFormModel
import sheridan.dheripu.prog20082_assignment3.ui.item.form.ItemFormUiState
import sheridan.dheripu.prog20082_assignment3.ui.navigation.ItemEntryDestination
import sheridan.dheripu.prog20082_assignment3.ui.theme.PROG20082_Assignment3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: ItemEntryViewModel
) {
    Scaffold(
        topBar = {
            ShoppingListTopAppBar(
                title = stringResource(ItemEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        ItemFormBody(
            itemFormUiState = viewModel.uiState,
            onNameChange = viewModel::onNameChange,
            onPriceChange = viewModel::onPriceChange,
            onPriorityChange = viewModel::onPriorityChange,
            onSaveClick = {
                viewModel.saveItem()
                navigateBack()
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemEntryScreenPreview() {
    PROG20082_Assignment3Theme {
        ItemFormBody(
            itemFormUiState = ItemFormUiState(
                ItemFormModel(
                    name = "Item name", price = "10.00", priority = Priority.LOW
                )
            ),
            onNameChange = {}, onPriceChange = {},
            onSaveClick = {}, onPriorityChange = {},
        )
    }
}
