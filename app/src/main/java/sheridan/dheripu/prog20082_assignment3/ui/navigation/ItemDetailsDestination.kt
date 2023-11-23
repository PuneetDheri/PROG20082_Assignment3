package sheridan.dheripu.prog20082_assignment3.ui.navigation

import sheridan.dheripu.prog20082_assignment3.R

object ItemDetailsDestination : NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.item_detail_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}