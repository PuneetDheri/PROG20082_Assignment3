package sheridan.dheripu.prog20082_assignment3.data.repository

import kotlinx.coroutines.flow.Flow
import sheridan.dheripu.prog20082_assignment3.domain.Item

interface ItemsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemByIdStream(id: Int): Flow<Item?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: Item)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: Item)

    suspend fun deleteItemById(id: Int)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: Item)

    suspend fun updateItemQuantityById(id: Int, quantity: Int)

    suspend fun updateItemSelectedById(id: Int, selected: Boolean)


}
