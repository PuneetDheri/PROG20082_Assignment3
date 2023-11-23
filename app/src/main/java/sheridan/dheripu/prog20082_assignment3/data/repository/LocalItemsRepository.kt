package sheridan.dheripu.prog20082_assignment3.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import sheridan.dheripu.prog20082_assignment3.data.local.ItemDao
import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.data.local.LocalItem
import sheridan.dheripu.prog20082_assignment3.domain.Item
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
class LocalItemsRepository(
    private val itemDao: ItemDao,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : ItemsRepository {

    @Inject
    constructor(itemDao: ItemDao) : this(itemDao, GlobalScope, Dispatchers.IO)

    override fun getAllItemsStream(): Flow<List<Item>> =
        itemDao.getAllItemsStream()
            .map{ list -> list.map { localItem ->  localItem.toItem() }}
            .flowOn(dispatcher)

    override fun getItemByIdStream(id: Int): Flow<Item?> =
        itemDao.getItemByIdStream(id)
            .map { localItem -> localItem?.toItem() }
            .flowOn(dispatcher)

    override suspend fun insertItem(item: Item) {
        externalScope.launch(dispatcher) { itemDao.insertItem(item.toLocalItem()) }.join()
    }

    override suspend fun deleteItem(item: Item) {
        externalScope.launch(dispatcher) { itemDao.deleteItem(item.toLocalItem()) }.join()
    }

    override suspend fun deleteItemById(id: Int){
        externalScope.launch(dispatcher) { itemDao.deleteItemById(id) }.join()
    }

    override suspend fun updateItem(item: Item) {
        externalScope.launch(dispatcher) { itemDao.updateItem(item.toLocalItem()) }.join()
    }



    override suspend fun updateItemSelectedById(id: Int, selected: Boolean) {
        externalScope.launch(dispatcher) { itemDao.updateItemSelectedById(id, selected) }.join()
    }

    override fun getItemsByPriorityStream(priority: Priority): Flow<List<LocalItem>> =
        itemDao.getItemsByPriority(priority)

}

fun LocalItem.toItem(): Item = Item(
    id = this.id,
    name = this.name,
    price = this.price,
    priority = this.priority,
    selected = this.selected
)

fun Item.toLocalItem(): LocalItem = LocalItem(
    id = this.id,
    name = this.name,
    price = this.price,
    priority = this.priority,
    selected = this.selected
)
