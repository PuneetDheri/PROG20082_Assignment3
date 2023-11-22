package sheridan.dheripu.prog20082_assignment3.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sheridan.dheripu.prog20082_assignment3.R
import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.domain.Item
import sheridan.dheripu.prog20082_assignment3.ui.model.ListItemModel
import sheridan.dheripu.prog20082_assignment3.ui.model.toListItemModel
import sheridan.dheripu.prog20082_assignment3.ui.common.ShoppingListTopAppBar
import sheridan.dheripu.prog20082_assignment3.ui.theme.PROG20082_Assignment3Theme
import sheridan.dheripu.prog20082_assignment3.ui.navigation.HomeDestination



/**
 * Entry route for Home screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemDetails: (Int) -> Unit,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val homeUiState:HomeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ShoppingListTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.item_entry_title)
                )
            }
        },
    ) { innerPadding ->
        HomeBody(
            itemList = homeUiState.itemList,
            onItemClick = navigateToItemDetails,
            onToggleSelect = viewModel::toggleSelect,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun HomeBody(
    itemList: List<ListItemModel>,
    onItemClick: (Int) -> Unit,
    onToggleSelect: (ListItemModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ShoppingListList(
                itemList = itemList,
                onItemClick = onItemClick,
                onToggleSelect = onToggleSelect,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun ShoppingListList(
    itemList: List<ListItemModel>,
    onItemClick: (Int) -> Unit,
    onToggleSelect: (ListItemModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = itemList, key = { it.id }) { item ->
            ShoppingListItem(
                item = item,
                onToggleSelect = onToggleSelect,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item.id) })
        }
    }
}

@Composable
private fun ShoppingListItem(
    item: ListItemModel,
    onToggleSelect: (ListItemModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Checkbox(checked = item.selected, onCheckedChange = { onToggleSelect(item)})
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
            ){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = item.price,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    PROG20082_Assignment3Theme {
        HomeBody(listOf(
            Item(1, "Game", 100.0, Priority.LOW).toListItemModel(),
            Item(2, "Pen", 200.0, Priority.MEDIUM, true).toListItemModel(),
            Item(3, "TV", 300.0, Priority.HIGH).toListItemModel()
        ), onItemClick = {}, onToggleSelect = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    PROG20082_Assignment3Theme {
        HomeBody(listOf(), onItemClick = {}, onToggleSelect = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListItemPreview() {
    PROG20082_Assignment3Theme {
        ShoppingListItem(
            Item(1, "Game", 100.0, Priority.LOW).toListItemModel(),
            onToggleSelect = {}
        )
    }
}
