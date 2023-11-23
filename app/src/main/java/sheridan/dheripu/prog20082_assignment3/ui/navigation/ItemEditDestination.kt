package sheridan.dheripu.prog20082_assignment3.ui.navigation

import sheridan.dheripu.prog20082_assignment3.R

object ItemEditDestination : NavigationDestination {
    override val route = "item_edit"
    override val titleRes = R.string.edit_item_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}